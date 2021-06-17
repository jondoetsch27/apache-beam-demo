package com.jdd.beam.demo.process;

import org.apache.beam.sdk.transforms.DoFn;

public class SplitWordsFn extends DoFn<String, String> {

  public static final String SPLIT_PATTERN = ":";

  @ProcessElement
  public void processElement(ProcessContext context) {
    for(String word: context.element().split(SPLIT_PATTERN)) {
      if (!word.isEmpty()) {
        context.output(word);
      }
    }
  }

}
