/*
 * Copyright 2017-2022 John Snow Labs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.goku.ghostify.common

import com.goku.ghostify.common.Annotated.{NerTaggedSentence, PosTaggedSentence}
import com.goku.ghostify.data.Annotation
import com.goku.ghostify.data.AnnotatorType.{NAMED_ENTITY, POS}

import scala.collection.Map

trait Tagged[T >: TaggedSentence <: TaggedSentence] extends Annotated[T] {
  val emptyTag = "O"

  override def unpack(annotations: Seq[Annotation]): Seq[T] = {

    val tokenized = TokenizedWithSentence.unpack(annotations)

    val tagAnnotations = annotations
      .filter(a => a.annotatorType == annotatorType)
      .sortBy(a => a.begin)
      .toIterator

    var annotation: Option[Annotation] = None

    tokenized.map { sentence =>
      val tokens = sentence.indexedTokens.map { token =>
        while (tagAnnotations.hasNext && (annotation.isEmpty || annotation.get.begin < token.begin))
          annotation = Some(tagAnnotations.next)

        val tag = if (annotation.isDefined && annotation.get.begin == token.begin) {
          annotation.get.result
        } else
          emptyTag
        // etract the confidence score belong to the tag
        val metadata =
          try {
            if (annotation.get.metadata.isDefinedAt("confidence"))
              Map(tag -> annotation.get.metadata("confidence"))
            else
              Map(tag -> annotation.get.metadata(tag))
          } catch {
            case _: Exception =>
              Map.empty[String, String]
          }

        IndexedTaggedWord(token.token, tag, token.begin, token.end, metadata = metadata)
      }

      new TaggedSentence(tokens)
    }
  }

  override def pack(items: Seq[T]): Seq[Annotation] = {
    items.zipWithIndex.flatMap { case (item, sentenceIndex) =>
      item.indexedTaggedWords.map { tag =>
        val metadata: Map[String, String] = if (tag.confidence.isDefined) {
          Map("word" -> tag.word) ++ tag.confidence
            .getOrElse(Array.empty[Map[String, String]])
            .flatten ++
            Map("sentence" -> sentenceIndex.toString)
        } else {
          Map("word" -> tag.word) ++ Map.empty[String, String] ++ Map(
            "sentence" -> sentenceIndex.toString
          )
        }
        new Annotation(annotatorType, tag.begin, tag.end, tag.tag, metadata)
      }
    }
  }
}

object PosTagged extends Tagged[PosTaggedSentence] {
  override def annotatorType: String = POS
}

object NerTagged extends Tagged[NerTaggedSentence] {
  override def annotatorType: String = NAMED_ENTITY

}
