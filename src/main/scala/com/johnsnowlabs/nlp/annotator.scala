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

package com.johnsnowlabs.nlp

import com.johnsnowlabs.ml.tensorflow.sentencepiece.ReadSentencePieceModel
import com.johnsnowlabs.nlp.annotators.classifier.dl._
import com.johnsnowlabs.nlp.annotators.cv.{
  ReadSwinForImageDLModel,
  ReadViTForImageDLModel,
  ReadablePretrainedSwinForImageModel,
  ReadablePretrainedViTForImageModel
}
import com.johnsnowlabs.nlp.annotators.ld.dl.{
  ReadLanguageDetectorDLTensorflowModel,
  ReadablePretrainedLanguageDetectorDLModel
}
import com.johnsnowlabs.nlp.annotators.ner.dl._
import com.johnsnowlabs.nlp.annotators.parser.dep.ReadablePretrainedDependency
import com.johnsnowlabs.nlp.annotators.parser.typdep.ReadablePretrainedTypedDependency
import com.johnsnowlabs.nlp.annotators.pos.perceptron.ReadablePretrainedPerceptron
import com.johnsnowlabs.nlp.annotators.sda.vivekn.ReadablePretrainedVivekn
import com.johnsnowlabs.nlp.annotators.seq2seq._
import com.johnsnowlabs.nlp.annotators.spell.norvig.ReadablePretrainedNorvig
import com.johnsnowlabs.nlp.annotators.spell.symmetric.ReadablePretrainedSymmetric
import com.johnsnowlabs.nlp.annotators.ws.ReadablePretrainedWordSegmenter
import com.johnsnowlabs.nlp.annotators.{
  ReadablePretrainedLemmatizer,
  ReadablePretrainedStopWordsCleanerModel,
  ReadablePretrainedTokenizer
}
import org.apache.spark.ml.util.DefaultParamsReadable

package object annotator {

  type Tokenizer = com.johnsnowlabs.nlp.annotators.Tokenizer

  object Tokenizer extends DefaultParamsReadable[Tokenizer]

  type TokenizerModel = com.johnsnowlabs.nlp.annotators.TokenizerModel

  object TokenizerModel extends ReadablePretrainedTokenizer

  type RegexTokenizer = com.johnsnowlabs.nlp.annotators.RegexTokenizer

  object RegexTokenizer extends DefaultParamsReadable[RegexTokenizer]

  type RecursiveTokenizer = com.johnsnowlabs.nlp.annotators.RecursiveTokenizer

  object RecursiveTokenizer extends DefaultParamsReadable[RecursiveTokenizer]

  type RecursiveTokenizerModel = com.johnsnowlabs.nlp.annotators.RecursiveTokenizerModel

  object RecursiveTokenizerModel extends ReadablePretrainedTokenizer

  type ChunkTokenizer = com.johnsnowlabs.nlp.annotators.ChunkTokenizer

  object ChunkTokenizer extends DefaultParamsReadable[ChunkTokenizer]

  type Token2Chunk = com.johnsnowlabs.nlp.annotators.Token2Chunk

  object Token2Chunk extends DefaultParamsReadable[Token2Chunk]

  type Normalizer = com.johnsnowlabs.nlp.annotators.Normalizer

  object Normalizer extends DefaultParamsReadable[Normalizer]

  type NormalizerModel = com.johnsnowlabs.nlp.annotators.NormalizerModel

  object NormalizerModel extends ParamsAndFeaturesReadable[NormalizerModel]

  type DateMatcher = com.johnsnowlabs.nlp.annotators.DateMatcher

  object DateMatcher extends DefaultParamsReadable[DateMatcher]

  type MultiDateMatcher = com.johnsnowlabs.nlp.annotators.MultiDateMatcher

  object MultiDateMatcher extends DefaultParamsReadable[MultiDateMatcher]

  type RegexMatcher = com.johnsnowlabs.nlp.annotators.RegexMatcher

  object RegexMatcher extends DefaultParamsReadable[RegexMatcher]

  type RegexMatcherModel = com.johnsnowlabs.nlp.annotators.RegexMatcherModel

  object RegexMatcherModel extends ParamsAndFeaturesReadable[RegexMatcherModel]

  type Chunker = com.johnsnowlabs.nlp.annotators.Chunker

  object Chunker extends DefaultParamsReadable[Chunker]

  type Stemmer = com.johnsnowlabs.nlp.annotators.Stemmer

  object Stemmer extends DefaultParamsReadable[Stemmer]

  type Lemmatizer = com.johnsnowlabs.nlp.annotators.Lemmatizer

  object Lemmatizer extends DefaultParamsReadable[Lemmatizer]

  type LemmatizerModel = com.johnsnowlabs.nlp.annotators.LemmatizerModel

  object LemmatizerModel extends ReadablePretrainedLemmatizer

  type StopWordsCleaner = com.johnsnowlabs.nlp.annotators.StopWordsCleaner

  object StopWordsCleaner
      extends DefaultParamsReadable[StopWordsCleaner]
      with ReadablePretrainedStopWordsCleanerModel

  type NGramGenerator = com.johnsnowlabs.nlp.annotators.NGramGenerator

  object NGramGenerator extends DefaultParamsReadable[NGramGenerator]


  type PerceptronApproach = com.johnsnowlabs.nlp.annotators.pos.perceptron.PerceptronApproach

  object PerceptronApproach extends DefaultParamsReadable[PerceptronApproach]

  type PerceptronApproachDistributed =
    com.johnsnowlabs.nlp.annotators.pos.perceptron.PerceptronApproachDistributed

  object PerceptronApproachDistributed
      extends DefaultParamsReadable[PerceptronApproachDistributed]

  type PerceptronModel = com.johnsnowlabs.nlp.annotators.pos.perceptron.PerceptronModel

  object PerceptronModel extends ReadablePretrainedPerceptron

  type SentenceDetector = com.johnsnowlabs.nlp.annotators.sbd.pragmatic.SentenceDetector

  object SentenceDetector extends DefaultParamsReadable[SentenceDetector]

  type SentimentDetector = com.johnsnowlabs.nlp.annotators.sda.pragmatic.SentimentDetector

  object SentimentDetector extends DefaultParamsReadable[SentimentDetector]

  type SentimentDetectorModel =
    com.johnsnowlabs.nlp.annotators.sda.pragmatic.SentimentDetectorModel

  object SentimentDetectorModel extends ParamsAndFeaturesReadable[SentimentDetectorModel]

  type ViveknSentimentApproach =
    com.johnsnowlabs.nlp.annotators.sda.vivekn.ViveknSentimentApproach

  object ViveknSentimentApproach extends DefaultParamsReadable[ViveknSentimentApproach]

  type ViveknSentimentModel = com.johnsnowlabs.nlp.annotators.sda.vivekn.ViveknSentimentModel

  object ViveknSentimentModel extends ReadablePretrainedVivekn

  type NorvigSweetingApproach =
    com.johnsnowlabs.nlp.annotators.spell.norvig.NorvigSweetingApproach

  object NorvigSweetingApproach extends DefaultParamsReadable[NorvigSweetingApproach]

  type NorvigSweetingModel = com.johnsnowlabs.nlp.annotators.spell.norvig.NorvigSweetingModel

  object NorvigSweetingModel extends ReadablePretrainedNorvig

  type SymmetricDeleteApproach =
    com.johnsnowlabs.nlp.annotators.spell.symmetric.SymmetricDeleteApproach

  object SymmetricDeleteApproach extends DefaultParamsReadable[SymmetricDeleteApproach]

  type SymmetricDeleteModel = com.johnsnowlabs.nlp.annotators.spell.symmetric.SymmetricDeleteModel

  object SymmetricDeleteModel extends ReadablePretrainedSymmetric

  type NerConverter = com.johnsnowlabs.nlp.annotators.ner.NerConverter

  object NerConverter extends ParamsAndFeaturesReadable[NerConverter]

  type DependencyParserApproach =
    com.johnsnowlabs.nlp.annotators.parser.dep.DependencyParserApproach

  object DependencyParserApproach extends DefaultParamsReadable[DependencyParserApproach]

  type DependencyParserModel = com.johnsnowlabs.nlp.annotators.parser.dep.DependencyParserModel

  object DependencyParserModel extends ReadablePretrainedDependency

  type TypedDependencyParserApproach =
    com.johnsnowlabs.nlp.annotators.parser.typdep.TypedDependencyParserApproach

  object TypedDependencyParserApproach
      extends DefaultParamsReadable[TypedDependencyParserApproach]

  type TypedDependencyParserModel =
    com.johnsnowlabs.nlp.annotators.parser.typdep.TypedDependencyParserModel

  object TypedDependencyParserModel extends ReadablePretrainedTypedDependency

  type NerOverwriter = com.johnsnowlabs.nlp.annotators.ner.NerOverwriter

  object NerOverwriter extends DefaultParamsReadable[NerOverwriter]

  type YakeKeywordExtraction = com.johnsnowlabs.nlp.annotators.keyword.yake.YakeKeywordExtraction

  object YakeKeywordExtraction extends ParamsAndFeaturesReadable[YakeKeywordExtraction]

  type LanguageDetectorDL = com.johnsnowlabs.nlp.annotators.ld.dl.LanguageDetectorDL

  object LanguageDetectorDL
      extends ReadablePretrainedLanguageDetectorDLModel
      with ReadLanguageDetectorDLTensorflowModel

  type WordSegmenterApproach = com.johnsnowlabs.nlp.annotators.ws.WordSegmenterApproach

  object WordSegmenterApproach extends DefaultParamsReadable[WordSegmenterApproach]

  type WordSegmenterModel = com.johnsnowlabs.nlp.annotators.ws.WordSegmenterModel

  object WordSegmenterModel extends ReadablePretrainedWordSegmenter

  type DocumentNormalizer = com.johnsnowlabs.nlp.annotators.DocumentNormalizer

  object DocumentNormalizer extends DefaultParamsReadable[DocumentNormalizer]

  type MarianTransformer = com.johnsnowlabs.nlp.annotators.seq2seq.MarianTransformer

  object MarianTransformer
      extends ReadablePretrainedMarianMTModel
      with ReadMarianMTDLModel
      with ReadSentencePieceModel

  type T5Transformer = com.johnsnowlabs.nlp.annotators.seq2seq.T5Transformer

  object T5Transformer
      extends ReadablePretrainedT5TransformerModel
      with ReadT5TransformerDLModel
      with ReadSentencePieceModel

  type BertForTokenClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.BertForTokenClassification

  object BertForTokenClassification
      extends ReadablePretrainedBertForTokenModel
      with ReadBertForTokenDLModel

  type DistilBertForTokenClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.DistilBertForTokenClassification

  object DistilBertForTokenClassification
      extends ReadablePretrainedDistilBertForTokenModel
      with ReadDistilBertForTokenDLModel

  type RoBertaForTokenClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.RoBertaForTokenClassification

  object RoBertaForTokenClassification
      extends ReadablePretrainedRoBertaForTokenModel
      with ReadRoBertaForTokenDLModel

  type XlmRoBertaForTokenClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.XlmRoBertaForTokenClassification

  object XlmRoBertaForTokenClassification
      extends ReadablePretrainedXlmRoBertaForTokenModel
      with ReadXlmRoBertaForTokenDLModel

  type AlbertForTokenClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.AlbertForTokenClassification

  object AlbertForTokenClassification
      extends ReadablePretrainedAlbertForTokenModel
      with ReadAlbertForTokenDLModel

  type XlnetForTokenClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.XlnetForTokenClassification

  object XlnetForTokenClassification
      extends ReadablePretrainedXlnetForTokenModel
      with ReadXlnetForTokenDLModel

  type LongformerForTokenClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.LongformerForTokenClassification

  object LongformerForTokenClassification
      extends ReadablePretrainedLongformerForTokenModel
      with ReadLongformerForTokenDLModel


  type BertForSequenceClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.BertForSequenceClassification

  object BertForSequenceClassification
      extends ReadablePretrainedBertForSequenceModel
      with ReadBertForSequenceDLModel

  type DistilBertForSequenceClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.DistilBertForSequenceClassification

  object DistilBertForSequenceClassification
      extends ReadablePretrainedDistilBertForSequenceModel
      with ReadDistilBertForSequenceDLModel

  type RoBertaForSequenceClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.RoBertaForSequenceClassification

  object RoBertaForSequenceClassification
      extends ReadablePretrainedRoBertaForSequenceModel
      with ReadRoBertaForSequenceDLModel

  type XlmRoBertaForSequenceClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.XlmRoBertaForSequenceClassification

  object XlmRoBertaForSequenceClassification
      extends ReadablePretrainedXlmRoBertaForSequenceModel
      with ReadXlmRoBertaForSequenceDLModel

  type LongformerForSequenceClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.LongformerForSequenceClassification

  object LongformerForSequenceClassification
      extends ReadablePretrainedLongformerForSequenceModel
      with ReadLongformerForSequenceDLModel

  type AlbertForSequenceClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.AlbertForSequenceClassification

  object AlbertForSequenceClassification
      extends ReadablePretrainedAlbertForSequenceModel
      with ReadAlbertForSequenceDLModel

  type XlnetForSequenceClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.XlnetForSequenceClassification

  object XlnetForSequenceClassification
      extends ReadablePretrainedXlnetForSequenceModel
      with ReadXlnetForSequenceDLModel

  type GPT2Transformer = com.johnsnowlabs.nlp.annotators.seq2seq.GPT2Transformer

  object GPT2Transformer
      extends ReadablePretrainedGPT2TransformerModel
      with ReadGPT2TransformerDLModel

  type DeBertaForSequenceClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.DeBertaForSequenceClassification

  object DeBertaForSequenceClassification
      extends ReadablePretrainedDeBertaForSequenceModel
      with ReadDeBertaForSequenceDLModel

  type DeBertaForTokenClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.DeBertaForTokenClassification

  object DeBertaForTokenClassification
      extends ReadablePretrainedDeBertaForTokenModel
      with ReadDeBertaForTokenDLModel

  type BertForQuestionAnswering =
    com.johnsnowlabs.nlp.annotators.classifier.dl.BertForQuestionAnswering

  object BertForQuestionAnswering
      extends ReadablePretrainedBertForQAModel
      with ReadBertForQuestionAnsweringDLModel

  type DistilBertForQuestionAnswering =
    com.johnsnowlabs.nlp.annotators.classifier.dl.DistilBertForQuestionAnswering

  object DistilBertForQuestionAnswering
      extends ReadablePretrainedDistilBertForQAModel
      with ReadDistilBertForQuestionAnsweringDLModel

  type RoBertaForQuestionAnswering =
    com.johnsnowlabs.nlp.annotators.classifier.dl.RoBertaForQuestionAnswering

  object RoBertaForQuestionAnswering
      extends ReadablePretrainedRoBertaForQAModel
      with ReadRoBertaForQuestionAnsweringDLModel

  type XlmRoBertaForQuestionAnswering =
    com.johnsnowlabs.nlp.annotators.classifier.dl.XlmRoBertaForQuestionAnswering

  object XlmRoBertaForQuestionAnswering
      extends ReadablePretrainedXlmRoBertaForQAModel
      with ReadXlmRoBertaForQuestionAnsweringDLModel

  type DeBertaForQuestionAnswering =
    com.johnsnowlabs.nlp.annotators.classifier.dl.DeBertaForQuestionAnswering

  object DeBertaForQuestionAnswering
      extends ReadablePretrainedDeBertaForQAModel
      with ReadDeBertaForQuestionAnsweringDLModel

  type AlbertForQuestionAnswering =
    com.johnsnowlabs.nlp.annotators.classifier.dl.AlbertForQuestionAnswering

  object AlbertForQuestionAnswering
      extends ReadablePretrainedAlbertForQAModel
      with ReadAlbertForQuestionAnsweringDLModel

  type LongformerForQuestionAnswering =
    com.johnsnowlabs.nlp.annotators.classifier.dl.LongformerForQuestionAnswering

  object LongformerForQuestionAnswering
      extends ReadablePretrainedLongformerForQAModel
      with ReadLongformerForQuestionAnsweringDLModel

  type ViTForImageClassification =
    com.johnsnowlabs.nlp.annotators.cv.ViTForImageClassification

  object ViTForImageClassification
      extends ReadablePretrainedViTForImageModel
      with ReadViTForImageDLModel

  type CamemBertForTokenClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.CamemBertForTokenClassification

  object CamemBertForTokenClassification
      extends ReadablePretrainedCamemBertForTokenModel
      with ReadCamemBertForTokenDLModel

  type TapasForQuestionAnswering =
    com.johnsnowlabs.nlp.annotators.classifier.dl.TapasForQuestionAnswering

  object TapasForQuestionAnswering
      extends ReadablePretrainedTapasForQAModel
      with ReadTapasForQuestionAnsweringDLModel

  type CamemBertForSequenceClassification =
    com.johnsnowlabs.nlp.annotators.classifier.dl.CamemBertForSequenceClassification

  object CamemBertForSequenceClassification
      extends ReadablePretrainedCamemBertForSequenceModel
      with ReadCamemBertForSequenceDLModel

  type SwinForImageClassification =
    com.johnsnowlabs.nlp.annotators.cv.SwinForImageClassification

  object SwinForImageClassification
      extends ReadablePretrainedSwinForImageModel
      with ReadSwinForImageDLModel

  type CamemBertForQuestionAnswering =
    com.johnsnowlabs.nlp.annotators.classifier.dl.CamemBertForQuestionAnswering

  object CamemBertForQuestionAnswering
      extends ReadablePretrainedCamemBertForQAModel
      with ReadCamemBertForQADLModel

  type ZeroShotNerModel =
    com.johnsnowlabs.nlp.annotators.ner.dl.ZeroShotNerModel

  object ZeroShotNerModel extends ReadablePretrainedZeroShotNer with ReadZeroShotNerDLModel

  type Date2Chunk = com.johnsnowlabs.nlp.annotators.Date2Chunk

  object Date2Chunk extends DefaultParamsReadable[Date2Chunk]

  type Chunk2Doc = com.johnsnowlabs.nlp.annotators.Chunk2Doc

  object Chunk2Doc extends DefaultParamsReadable[Chunk2Doc]

}
