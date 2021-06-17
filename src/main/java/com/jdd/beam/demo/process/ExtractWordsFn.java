package com.jdd.beam.demo.process;

import org.apache.beam.sdk.transforms.DoFn;

public class ExtractWordsFn extends DoFn<String, String> {

  public static final String TOKENIZER_PATTERN = "[^\\p{L}]+";

  @ProcessElement
  public void processElement(ProcessContext context) {
    for(String word: context.element().split(TOKENIZER_PATTERN)) {
      if (!word.isEmpty()) {
        context.output(word);
      }
    }
  }

}
