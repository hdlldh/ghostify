package com.goku.ghostify.nlp.spark

import com.goku.ghostify.util.SentenceExtractor
import com.johnsnowlabs.nlp.Annotation
import com.johnsnowlabs.nlp.AnnotatorType.DOCUMENT
import org.apache.spark.ml.util.{DefaultParamsReadable, Identifiable}

class SentenceDetector(override val uid: String)
    extends AnnotatorModel[SentenceDetector] with HasSimpleAnnotate[SentenceDetector] {

  override val outputAnnotatorType: AnnotatorType = DOCUMENT

  override val inputAnnotatorTypes: Array[AnnotatorType] = Array(DOCUMENT)

  private final val SentenceSplitter = "(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=[.?!])\\s+".r
  def this() = this(Identifiable.randomUID("SentenceDetector"))
  def annotate(annotations: Seq[Annotation]): Seq[Annotation] = {
    val docs = annotations.map(_.result)
    docs.flatMap { doc =>
      val sentences = SentenceExtractor.split(doc)
      sentences.sortBy(_.start).map(r =>
        Annotation(DOCUMENT, r.start, r.end, r.content, Map("sentence" -> r.index.toString))
      )
    }
  }
}

object SentenceDetector extends DefaultParamsReadable[SentenceDetector]
