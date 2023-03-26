package com.goku.ghostify.common

import com.goku.ghostify.data.Annotation

trait Annotated[TResult] {
  def annotatorType: String

  def unpack(annotations: Seq[Annotation]): Seq[TResult]

  def pack(items: Seq[TResult]): Seq[Annotation]
}

object Annotated {
  type PosTaggedSentence = TaggedSentence
  type NerTaggedSentence = TaggedSentence
}
