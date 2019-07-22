package com.qf.shop_search_service;

import com.qf.entity.Goods;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopSearchServiceApplicationTests {

	@Autowired
	private SolrClient solrClient;

	/**
	 * 添加索引库
	 * 添加时不设置id的话，它会自动生成id
	 */
	@Test
	public void add() throws IOException, SolrServerException {
		//创建一个solr文档 —— 搜索的一条条目 对应数据库中的一条记录
		SolrInputDocument document = new SolrInputDocument();
		document.addField("gname","小米手机4");
		document.addField("ginfo","小米手机，冬天再也不怕冷了");
		document.addField("gimage","http://image.so.com/z?a=viewPage&ch=pet&t1=234&src=banner_pet&gid=52ccb0fb7f9aef6af43fef025a10854d&ancestor=list&clw=326#grpid=52ccb0fb7f9aef6af43fef025a10854d&id=16fb69197e7fd59a91c7721161320f6f&dataindex=1");
		document.addField("gprice","2480.59");
		document.addField("gsave","10000");
		solrClient.add(document);
		solrClient.commit();
	}

	/**
	 * 当id相同时就是修改，id不同时就是添加
	 * @throws IOException
	 * @throws SolrServerException
	 */
	@Test
	public void update() throws IOException, SolrServerException {
		//创建一个solr文档 —— 搜索的一条条目 对应数据库中的一条记录
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id","3");
		document.addField("gname","古剑奇谭");
		document.addField("ginfo","这是个良心游戏，新出了个刀职业");
		document.addField("gimage","http://image.so.com/view?q=%E5%8F%A4%E5%89%91%E5%A5%87%E8%B0%AD%E7%BD%91%E7%BB%9C%E7%89%88&src=srp&correct=%E5%8F%A4%E5%89%91%E5%A5%87%E8%B0%AD%E7%BD%91%E7%BB%9C%E7%89%88&ancestor=list&cmsid=eba51347f33bfecf96ad23d246d8a13a&cmran=0&cmras=0&cn=0&gn=0&kn=29&fsn=109&adstar=0&clw=254#id=56f910848e123105878d2532170a04c1&currsn=0&ps=100&pc=100");
		document.addField("gprice","2000");
		document.addField("gsave","5000");
		solrClient.add(document);
		solrClient.commit();
	}

	/**
	 * 删除索引
	 * @return
	 */
	@Test
	public  void   delete() throws IOException, SolrServerException {
		//用id删除
		solrClient.deleteById("2103bfbf-7ca1-4f1b-ac62-8aa01e1ce803");
		//删掉查询到的索引
		//solrClient.deleteByQuery("*:*");
		solrClient.commit();
	}

	/**
	 * 查询
	 */
	@Test
	public void query(){
		SolrQuery query =new SolrQuery("gname:刀 || ginfo:刀");
		
		try {
			QueryResponse response = solrClient.query(query);
			SolrDocumentList results = response.getResults();
			for (SolrDocument result : results) {
				Goods goods = new Goods();
				goods.setId((Integer) result.getFieldValue("id"));
				goods.setGname((String) result.getFieldValue("gname"));
				goods.setGinfo((String) result.getFieldValue("ginfo"));
			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
