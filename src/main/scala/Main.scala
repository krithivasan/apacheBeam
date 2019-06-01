
import org.apache.beam.sdk.Pipeline
import org.apache.beam.sdk.io.FileIO
import org.apache.beam.sdk.io.fs.MatchResult
import org.apache.beam.sdk.options.{PipelineOptions, PipelineOptionsFactory}
import org.apache.beam.sdk.transforms.DoFn.ProcessElement
import org.apache.beam.sdk.transforms.{DoFn, ParDo}


/**
  * extract the file name
  */
class GetFileName
  extends DoFn[MatchResult.Metadata, Unit]
    with Serializable {

  @ProcessElement
  def processElement(c: ProcessContext): Unit = {
    // extract the filename
    println(c.element().resourceId().toString)
  }
}

/**
  *
  */
object Main extends App {

  val options: PipelineOptions = PipelineOptionsFactory.fromArgs(args: _*).create()

  val pipeline = Pipeline.create(options)

  val inputFilePattern = "/Users/rumeshkrishnan/*" //"s3:// / /*.csv.gz"

  // pipeline to read the file and print the url
  pipeline
    .apply(
      FileIO.`match`().filepattern(inputFilePattern)
    )
    .apply(
      ParDo.of(new GetFileName())
    )

  // execute the file
  pipeline.run().waitUntilFinish()

}
