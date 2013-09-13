package test.testStorm.tutorial2;

import java.util.Map;

import redis.clients.jedis.Jedis;
import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class WordCountBolt extends BaseRichBolt
{
//	private Jedis jedis = new Jedis("10.20.12.91", 6379);
	private Jedis jedis;
	private OutputCollector collector;

	@Override
	public void prepare(Map stormConf, TopologyContext context, OutputCollector collector)
	{
		this.collector = collector;
		this.jedis = new Jedis("10.20.12.91", 6379);
	}

	@Override
	public void execute(Tuple input)
	{
		String word = input.getString(0);
		Long count = this.jedis.incr(word);
		this.collector.emit(new Values(word, count));
		this.collector.ack(input);
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer)
	{
		declarer.declare(new Fields("word", "count"));
	}

	@Override
	public void cleanup()
	{
		this.jedis.disconnect();
		super.cleanup();
	}
}
