package test.testStorm.tutorial2;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

public class WordCountTopology
{
	public static void main(String [] args) throws InterruptedException
	{
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("sentences", new RandomSentenceSpout(), 5);
		builder.setBolt("split", new SplitSentenceBolt(), 8).shuffleGrouping("sentences");
		builder.setBolt("count", new WordCountBolt(), 12).fieldsGrouping("split", new Fields("word"));
		
		
		
		
		Config conf = new Config();
		conf.setDebug(true);
		conf.put(Config.TOPOLOGY_FALL_BACK_ON_JAVA_SERIALIZATION, false);
		
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("word count", conf, builder.createTopology());
		Thread.sleep(5000);
		cluster.killTopology("word count");
		cluster.shutdown();
	}
}
