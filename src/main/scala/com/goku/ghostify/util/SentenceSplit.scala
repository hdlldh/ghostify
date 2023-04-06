package com.goku.ghostify.util

import com.goku.ghostify.common.Sentence
import com.goku.ghostify.data.{Annotation, AnnotatorType}

object SentenceSplit {

  def unpack(annotations: Seq[Annotation]): Seq[Sentence] = {
    annotations.filter(_.annotatorType == AnnotatorType.DOCUMENT).map { annotation =>
      val index: Int = annotation.metadata.getOrElse("sentence", "0").toInt
      Sentence(
        annotation.result,
        annotation.begin,
        annotation.end,
        index,
        Option(annotation.metadata)
      )
    }
  }

  def pack(items: Seq[Sentence]): Seq[Annotation] = {
    items.sortBy(i => i.start).zipWithIndex.map { case (item, index) =>
      Annotation(
        AnnotatorType.DOCUMENT,
        item.start,
        item.end,
        item.content,
        Map("sentence" -> index.toString)
      )
    }
  }
}
