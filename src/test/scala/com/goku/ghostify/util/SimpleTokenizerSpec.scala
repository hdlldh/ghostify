package com.goku.ghostify.util

import com.johnsnowlabs.nlp.annotators.common.{IndexedToken, Sentence, TokenizedSentence}
import org.scalatest.wordspec.AnyWordSpec

class SimpleTokenizerSpec extends AnyWordSpec {

  val tokenizer = new SimpleTokenizer()
  "Simple Tokenizer" should {

    "tokenize one sentence correctly" in {
      val text = "Happy 5-year-old birthday 🎁, Alex!"
      val sentences = Seq(Sentence(text, 0, text.length - 1, 0))
      val encoded = tokenizer.encode(sentences)
      val expected = Seq(
        TokenizedSentence(
          Array(
            IndexedToken("Happy", 0, 4),
            IndexedToken("5-year-old", 6, 15),
            IndexedToken("birthday", 17, 24),
            IndexedToken("🎁", 26, 27),
            IndexedToken(",", 28, 28),
            IndexedToken("Alex", 30, 33),
            IndexedToken("!", 34, 34)
          ),
          0
        )
      )
      
      assert(encoded.map(_.sentenceIndex) === expected.map(_.sentenceIndex))
      assert(encoded.flatMap(_.indexedTokens) === expected.flatMap(_.indexedTokens))

    }

  }
}
