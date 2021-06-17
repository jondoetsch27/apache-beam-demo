package com.jdd.beam.demo.pipeline;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.PipelineOptions;

public interface WordCountOptions extends PipelineOptions {

  @Default.String("./src/main/resources/input.txt")
  String getInputFile();
  void setInputFile(String value);

  @Default.String("./src/main/resources/output.txt")
  String getOutputFile();
  void setOutputFile(String value);
}
