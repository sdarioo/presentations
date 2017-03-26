package com.example.one_to_one;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.AbstractTest;
import com.example.domain.one_to_one.Post1;
import com.example.domain.one_to_one.Post2;
import com.example.domain.one_to_one.Post3;
import com.example.domain.one_to_one.PostDetails1;
import com.example.domain.one_to_one.PostDetails2;
import com.example.domain.one_to_one.PostDetails3;
import com.example.domain.one_to_one.PostRepository1;
import com.example.domain.one_to_one.PostRepository2;
import com.example.domain.one_to_one.PostRepository3;

import net.ttddyy.dsproxy.QueryCountHolder;

public class TestOneToOne extends AbstractTest {

	@Autowired
	private PostRepository1 repository1;
	@Autowired
	private PostRepository2 repository2;
	@Autowired
	private PostRepository3 repository3;
	
	private static int OBJ_COUNT = 1_000;
	
	@Test
	public void lazyWithNPlus1Problem() throws Exception {
		
		addPosts1(OBJ_COUNT);
		
		QueryCountHolder.clear();
		time(repository1::findAll, "OneToOne_Lazy_N+1");
		assertEquals(1 + OBJ_COUNT, QueryCountHolder.getGrandTotal().getSelect());
	}
	
	@Test
	public void lazyWithMapsId() throws Exception {
		
		addPosts2(OBJ_COUNT);
		
		QueryCountHolder.clear();
		time(repository2::findAll, "OneToOne_Lazy_MapsId");
		assertEquals(1, QueryCountHolder.getGrandTotal().getSelect());
	}
	
	@Test
	public void eager() throws Exception {
		addPosts3(OBJ_COUNT);
		
		QueryCountHolder.clear();
		time(repository3::findAll, "OneToOne_Eager");
		assertEquals(1001, QueryCountHolder.getGrandTotal().getSelect());
		
		QueryCountHolder.clear();
		time(repository3::findAllWithDetails, "OneToOne_Eager_JoinFetch");
		assertEquals(1, QueryCountHolder.getGrandTotal().getSelect());
	}
	
	

	private void addPosts1(int count) {
		doInJPA(() -> {
			for (int i = 0; i < count; i++) {
				Post1 post = new Post1();
				em.persist(post);
				em.persist(new PostDetails1(post));
			}
		}, null);
	}
	
	private void addPosts2(int count) {
		doInJPA(() -> {
			for (int i = 0; i < count; i++) {
				Post2 post = new Post2();
				em.persist(post);
				em.persist(new PostDetails2(post));
			}
		}, null);
	}
	
	private void addPosts3(int count) {
		doInJPA(() -> {
			for (int i = 0; i < count; i++) {
				Post3 post = new Post3();
				em.persist(post);
				em.persist(new PostDetails3(post));
			}
		}, null);
	}
}
