package com.goku.ghostify.nlp.spark
import com.johnsnowlabs.nlp.Annotation
import com.johnsnowlabs.nlp.AnnotatorType.{DOCUMENT, TOKEN}
import org.apache.spark.ml.param.{BooleanParam, Param}

class TokenizerModel(override val uid: String)
  extends AnnotatorModel[TokenizerModel] with HasSimpleAnnotate[TokenizerModel] {

  override val inputAnnotatorTypes: Array[AnnotatorType] = Array[AnnotatorType](DOCUMENT)

  override val outputAnnotatorType: AnnotatorType = TOKEN

  val targetPattern: Param[String] = new Param(
    this,
    "targetPattern",
    "pattern to grab from text as token candidates. Defaults \\S+")

  val caseSensitiveExceptions: BooleanParam = new BooleanParam(
    this,
    "caseSensitiveExceptions",
    "Whether to care for case sensitiveness in exceptions")

  setDefault(targetPattern -> "\\S+", caseSensitiveExceptions -> true)


  override def annotate(annotations: Seq[Annotation]): Seq[Annotation] = {
    annotations

  }

}
