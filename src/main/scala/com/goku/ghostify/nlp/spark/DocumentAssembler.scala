package com.goku.ghostify.nlp.spark

import com.johnsnowlabs.nlp.Annotation
import com.johnsnowlabs.nlp.AnnotatorType.DOCUMENT
import org.apache.spark.ml.UnaryTransformer
import org.apache.spark.ml.util.{DefaultParamsWritable, Identifiable}
import org.apache.spark.sql.types._

class DocumentAssembler(override val uid: String)
    extends UnaryTransformer[String, Seq[Annotation], DocumentAssembler]
    with DefaultParamsWritable {

  def this() = this(Identifiable.randomUID("DocumentAssembler"))

  override protected def createTransformFunc: String => Seq[Annotation] = { text =>
    val _text = Option(text).getOrElse("").trim
    if (_text.isEmpty) Seq.empty[Annotation]
    else Seq(Annotation(DOCUMENT, 0, _text.length - 1, _text, Map("sentence" -> "0")))
  }

  override protected def validateInputType(inputType: DataType): Unit =
    require(inputType == StringType)

  override protected def outputDataType: DataType = ArrayType(Annotation.dataType)

  override final def transformSchema(schema: StructType): StructType = {
    val inputType = schema($(inputCol)).dataType
    validateInputType(inputType)
    if (schema.fieldNames.contains($(outputCol))) {
      throw new IllegalArgumentException(s"Output column ${$(outputCol)} already exists.")
    }

    val metadataBuilder: MetadataBuilder = new MetadataBuilder()
    metadataBuilder.putString("annotatorType", DOCUMENT)
    val outputFields = schema.fields :+
      StructField(
        getOutputCol,
        ArrayType(Annotation.dataType),
        nullable = false,
        metadataBuilder.build
      )
    StructType(outputFields)
  }

}
