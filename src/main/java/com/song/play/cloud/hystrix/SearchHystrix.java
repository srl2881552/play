package com.song.play.cloud.hystrix;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.song.play.cloud.entity.PageBean;
import com.song.play.cloud.entity.Search;
import com.song.play.cloud.entity.SearchPageBean;
import com.song.play.cloud.service.SearchApi;

import feign.RequestLine;
@Component
public class SearchHystrix implements SearchApi{

	@Override
	@RequestMapping(value = "/search/saveIndex", method = RequestMethod.POST)
	public void saveIndex(Search model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value = "/search/updateIndex", method = RequestMethod.POST)
	public void updateIndex(Search model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value = "/search/removeIndex", method = RequestMethod.POST)
	public void removeIndex(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value = "/search/removeAll", method = RequestMethod.POST)
	public void removeAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value = "/search/refresh", method = RequestMethod.POST)
	public void refresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	@RequestMapping(value = "/search/searchLiveSingle", method = RequestMethod.POST)
	public SearchPageBean<Search> searchLiveSingle(String field, String sortField,
			String search, int curPage, int pageSize) {
		// TODO Auto-generated method stub
		return new SearchPageBean<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchLiveMultiple", method = RequestMethod.POST)
	public SearchPageBean<Search> searchLiveMultiple(
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "search", required = true) String search,
			@RequestParam(value = "curPage", required = true) int curPage,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		// TODO Auto-generated method stub
		return new SearchPageBean<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchLiveCate", method = RequestMethod.POST)
	public SearchPageBean<Search> searchLiveCate(
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "search", required = true) String search,
			@RequestParam(value = "curPage", required = true) int curPage,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		// TODO Auto-generated method stub
		return new SearchPageBean<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchLiveRange", method = RequestMethod.POST)
	public SearchPageBean<Search> searchLiveRange(
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "startTime", required = true) Long startTime,
			@RequestParam(value = "endTime", required = true) Long endTime,
			@RequestParam(value = "curPage", required = true) int curPage,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		// TODO Auto-generated method stub
		return new SearchPageBean<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchVideoSingle", method = RequestMethod.POST)
	public SearchPageBean<Search> searchVideoSingle(
			@RequestParam(value = "field", required = true) String field,
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "search", required = true) String search,
			@RequestParam(value = "curPage", required = true) int curPage,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		// TODO Auto-generated method stub
		return new SearchPageBean<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchVideoMultiple", method = RequestMethod.POST)
	public SearchPageBean<Search> searchVideoMultiple(
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "search", required = true) String search,
			@RequestParam(value = "curPage", required = true) int curPage,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		// TODO Auto-generated method stub
		return new SearchPageBean<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchVideoCate", method = RequestMethod.POST)
	public SearchPageBean<Search> searchVideoCate(
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "search", required = true) String search,
			@RequestParam(value = "curPage", required = true) int curPage,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		// TODO Auto-generated method stub
		return new SearchPageBean<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchVideoAll", method = RequestMethod.POST)
	public SearchPageBean<Search> searchVideoAll(
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "curPage", required = true) int curPage,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		// TODO Auto-generated method stub
		return new SearchPageBean<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchLiveAll", method = RequestMethod.POST)
	public SearchPageBean<Search> searchLiveAll(
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "curPage", required = true) int curPage,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		// TODO Auto-generated method stub
		return new SearchPageBean<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchVideoRange", method = RequestMethod.POST)
	public SearchPageBean<Search> searchVideoRange(
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "startTime", required = true) Long startTime,
			@RequestParam(value = "endTime", required = true) Long endTime,
			@RequestParam(value = "curPage", required = true) int curPage,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		// TODO Auto-generated method stub
		return new SearchPageBean<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchSingle", method = RequestMethod.POST)
	public SearchPageBean<Search> searchSingle(
			String field,
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "search", required = true) String search,
			@RequestParam(value = "curPage", required = true) int curPage,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		// TODO Auto-generated method stub
		return new SearchPageBean<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchMultiple", method = RequestMethod.POST)
	public SearchPageBean<Search> searchMultiple(
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "search", required = true) String search,
			@RequestParam(value = "curPage", required = true) int curPage,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		// TODO Auto-generated method stub
		return new SearchPageBean<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchCate", method = RequestMethod.POST)
	public SearchPageBean<Search> searchCate(
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "search", required = true) String search,
			@RequestParam(value = "curPage", required = true) int curPage,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		// TODO Auto-generated method stub
		return new SearchPageBean<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchRange", method = RequestMethod.POST)
	public SearchPageBean<Search> searchRange(
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "startTime", required = true) Long startTime,
			@RequestParam(value = "endTime", required = true) Long endTime,
			@RequestParam(value = "curPage", required = true) int curPage,
			@RequestParam(value = "pageSize", required = true) int pageSize) {
		// TODO Auto-generated method stub
		return new SearchPageBean<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchConform", method = RequestMethod.POST)
	public List<Search> searchConform(
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "search", required = true) String search) {
		// TODO Auto-generated method stub
		return new ArrayList<Search>();
	}

	@Override
	@RequestMapping(value = "/search/searchGroup", method = RequestMethod.POST)
	public List<Search> searchGroup(
			@RequestParam(value = "sortField", required = true) String sortField,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "indexCount", required = true) Integer indexCount) {
		// TODO Auto-generated method stub
		return new ArrayList<Search>();
	}

	

}
