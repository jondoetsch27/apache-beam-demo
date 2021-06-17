package com.jdd.beam.demo.pipeline;

import com.jdd.beam.demo.transform.CountWords;
import com.jdd.beam.demo.transform.SplitWords;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.TypeDescriptors;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WordCountRunner implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    WordCountOptions options = PipelineOptionsFactory.fromArgs(args).withoutStrictParsing()
        .as(WordCountOptions.class);
    runWordCount(options);
  }

  static void runWordCount(WordCountOptions options) throws InterruptedException {
    Pipeline pipeline = Pipeline.create(options);
    pipeline.apply("Reading Text", TextIO.read().from(options.getInputFile()))
        .apply(new SplitWords())
        .apply(new CountWords())
        .apply("FormatResults", MapElements
            .into(TypeDescriptors.strings())
            .via((KV<String, Long> wordCount) -> wordCount.getKey() + ": " + wordCount.getValue()))
        .apply("WriteCounts", TextIO.write().to(options.getOutputFile()));
    pipeline.run().waitUntilFinish();
  }

}
