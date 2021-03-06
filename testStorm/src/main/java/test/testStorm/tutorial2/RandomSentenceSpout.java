package test.testStorm.tutorial2;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class RandomSentenceSpout extends BaseRichSpout
{
	private SpoutOutputCollector collector;
	private Random rand = new Random();

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector)
	{
		this.collector = collector;
	}

	@Override
	public void nextTuple()
	{
	    Utils.sleep(100);
	    String[] sentences = new String[] {
	        "the cow jumped over the moon",
	        "an apple a day keeps the doctor away",
	        "four score and seven years ago",
	        "snow white and the seven dwarfs",
	        "i am at two with nature"};
	    String sentence = sentences[rand.nextInt(sentences.length)];
	    collector.emit(new Values(sentence));
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer)
	{
		declarer.declare(new Fields("word"));
	}

}
