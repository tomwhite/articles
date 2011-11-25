package org.tiling.hadoop_aws_article;

import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class AccessLogFileAnalyzer {

  public static class MapClass extends MapReduceBase implements Mapper {

    private final static LongWritable ONE = new LongWritable(1);

    private static Pattern p = Pattern
        .compile("([^ ]*) ([^ ]*) ([^ ]*) \\[([^]]*)\\] \"([^\"]*)\"" +
                        " ([^ ]*) ([^ ]*).*");

    private static DateTimeFormatter formatter = DateTimeFormat
        .forPattern("dd/MMM/yyyy:HH:mm:ss Z");

    private IntWritable minute = new IntWritable();

    public void map(WritableComparable key, Writable value,
        OutputCollector output, Reporter reporter) throws IOException {

      String line = ((Text) value).toString();
      Matcher matcher = p.matcher(line);
      if (matcher.matches()) {
        String timestamp = matcher.group(4);
        minute.set(getMinuteBucket(timestamp));
        output.collect(minute, ONE);
      }

    }

    private int getMinuteBucket(String timestamp) {
      DateTime dt = formatter.parseDateTime(timestamp);
      return dt.getMinuteOfDay() + (dt.getDayOfWeek() - 1)
          * DateTimeConstants.MINUTES_PER_DAY;
    }

  }

  public static class ReduceClass extends MapReduceBase implements Reducer {

    public void reduce(WritableComparable key, Iterator values,
        OutputCollector output, Reporter reporter) throws IOException {
      int sum = 0;
      while (values.hasNext()) {
        sum += ((LongWritable) values.next()).get();
      }
      output.collect(key, new LongWritable(sum));
    }
  }
	

  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.err
          .println("Usage: AccessLogFileAnalyzer <input path> <output path>");
      System.exit(-1);
    }

    JobConf conf = new JobConf(AccessLogFileAnalyzer.class);

    conf.setInputPath(new Path(args[0]));
    conf.setOutputPath(new Path(args[1]));

    conf.setOutputKeyClass(IntWritable.class);
    conf.setOutputValueClass(LongWritable.class);

    conf.setMapperClass(MapClass.class);
    conf.setCombinerClass(ReduceClass.class);
    conf.setReducerClass(ReduceClass.class);

    conf.setNumReduceTasks(1);

    JobClient.runJob(conf);
  }

}
