package com.goku.ghostify.util

import com.johnsnowlabs.nlp.annotators.common.Sentence
import org.scalatest.wordspec.AnyWordSpec

class SentenceExtractorSpec extends AnyWordSpec {

  "Sentence Extractor" should {

    "extract one sentence correctly" in {

      val text = "Happy birthday 🎁, Alex!"

      val extracted = SentenceExtractor.split(text)
      val expected = Seq(Sentence(text, 0, text.length - 1, 0, None))
      assert(extracted === expected)

    }

    "extract two sentences correctly" in {

      val text = "Happy birthday 🎁, Alex!  Enjoy your time 🎉"
      val sentence1 = "Happy birthday 🎁, Alex!"
      val sentence2 = "Enjoy your time 🎉"

      val extracted = SentenceExtractor.split(text)
      val expected = Seq(
        Sentence(sentence1, 0, sentence1.length - 1, 0, None),
        Sentence(sentence2, sentence1.length + 2, sentence1.length + sentence2.length + 1, 1, None)
      )
      assert(extracted === expected)

    }

  }

}
