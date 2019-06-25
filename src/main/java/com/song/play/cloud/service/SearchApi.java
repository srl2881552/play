package com.song.play.cloud.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.song.play.cloud.entity.PageBean;
import com.song.play.cloud.entity.Search;
import com.song.play.cloud.entity.SearchPageBean;
import com.song.play.cloud.hystrix.SearchHystrix;
import com.song.play.cloud.hystrix.VideoHystrix;
import com.song.play.cloud.security.FeignSearch;
import com.song.play.cloud.security.FeignUser;

import feign.RequestLine;

@FeignClient(name = "play-search-api",configuration=FeignSearch.class,fallback = SearchHystrix.class)
public interface SearchApi {

	/**
	 * 保存索引
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/save",method = RequestMethod.GET)
	public void saveIndex(@RequestBody Search model);
	/**
	 * 更新索引
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/update",method = RequestMethod.GET)
	public void updateIndex(@RequestBody Search model);
	/**
	 * 删除索引
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/delete",method = RequestMethod.GET)
	public void removeIndex(@RequestParam(value = "id", required = true) String id);
	/**
	 * 清空索引
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/removeAll",method = RequestMethod.GET)
	public void removeAll();
	/**
	 * 刷新全量索引
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/refresh",method = RequestMethod.GET)
	public void refresh();
	/**
	 * 单域搜索
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/searchLiveSingle",method = RequestMethod.GET)
	public SearchPageBean<Search> searchLiveSingle(@RequestParam(value = "field", required = true) String field, @RequestParam(value = "sortField", required = true) String sortField,@RequestParam(value = "search", required = true) String search,@RequestParam(value = "curPage", required = true) int curPage,@RequestParam(value = "pageSize", required = true) int pageSize);
	/**
	 * 多域搜索
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/searchLiveMultiple",method = RequestMethod.GET)
	public SearchPageBean<Search> searchLiveMultiple(@RequestParam(value = "sortField", required = true) String sortField,@RequestParam(value = "search", required = true) String search, @RequestParam(value = "curPage", required = true) int curPage,@RequestParam(value = "pageSize", required = true) int pageSize);
	/**
	 * 类型搜索
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/searchLiveCate",method = RequestMethod.GET)
	public SearchPageBean<Search> searchLiveCate(@RequestParam(value = "sortField", required = true) String sortField,@RequestParam(value = "search", required = true) String search,@RequestParam(value = "curPage", required = true) int curPage,@RequestParam(value = "pageSize", required = true) int pageSize);
	/**
	 * 时间范围搜索
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/searchLiveRange",method = RequestMethod.GET)
	public SearchPageBean<Search> searchLiveRange(@RequestParam(value = "sortField", required = true) String sortField,@RequestParam(value = "startTime", required = true) Long startTime,@RequestParam(value = "endTime", required = true) Long endTime, @RequestParam(value = "curPage", required = true) int curPage,@RequestParam(value = "pageSize", required = true) int pageSize);
	
	
	/**
	 * 单域搜索
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/searchVideoSingle",method = RequestMethod.GET)
	public SearchPageBean<Search> searchVideoSingle(@RequestParam(value = "field", required = true) String field,@RequestParam(value = "sortField", required = true) String sortField,@RequestParam(value = "search", required = true) String search, @RequestParam(value = "curPage", required = true) int curPage,@RequestParam(value = "pageSize", required = true) int pageSize);
	/**
	 * 多域搜索
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/searchVideoMultiple",method = RequestMethod.GET)
	public SearchPageBean<Search> searchVideoMultiple(@RequestParam(value = "sortField", required = true) String sortField,@RequestParam(value = "search", required = true) String search, @RequestParam(value = "curPage", required = true) int curPage,@RequestParam(value = "pageSize", required = true) int pageSize);
	/**
	 * 类型搜索
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/searchVideoCate",method = RequestMethod.GET)
	public SearchPageBean<Search> searchVideoCate(@RequestParam(value = "sortField", required = true) String sortField,@RequestParam(value = "search", required = true) String search, @RequestParam(value = "curPage", required = true) int curPage,@RequestParam(value = "pageSize", required = true) int pageSize);
	/**
	 * 类型搜索
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/searchVideoAll",method = RequestMethod.GET)
	public SearchPageBean<Search> searchVideoAll(@RequestParam(value = "sortField", required = true) String sortField, @RequestParam(value = "curPage", required = true) int curPage,@RequestParam(value = "pageSize", required = true) int pageSize);
	/**
	 * 类型搜索
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/searchLiveAll",method = RequestMethod.GET)
	public SearchPageBean<Search> searchLiveAll(@RequestParam(value = "sortField", required = true) String sortField, @RequestParam(value = "curPage", required = true) int curPage,@RequestParam(value = "pageSize", required = true) int pageSize);
	/**
	 * 时间范围搜索
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/searchVideoRange",method = RequestMethod.GET)
	public SearchPageBean<Search> searchVideoRange(@RequestParam(value = "sortField", required = true) String sortField,@RequestParam(value = "startTime", required = true) Long startTime,@RequestParam(value = "endTime", required = true) Long endTime, @RequestParam(value = "curPage", required = true) int curPage,@RequestParam(value = "pageSize", required = true) int pageSize);
	@RequestMapping(value = "/search/searchSingle",method = RequestMethod.GET)
	public SearchPageBean<Search> searchSingle(String field,@RequestParam(value = "sortField", required = true) String sortField,@RequestParam(value = "search", required = true) String search, @RequestParam(value = "curPage", required = true) int curPage,@RequestParam(value = "pageSize", required = true) int pageSize);
	/**
	 * 多域搜索
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/searchMultiple",method = RequestMethod.GET)
	public SearchPageBean<Search> searchMultiple(@RequestParam(value = "sortField", required = true) String sortField,@RequestParam(value = "search", required = true) String search, @RequestParam(value = "curPage", required = true) int curPage,@RequestParam(value = "pageSize", required = true) int pageSize);
	/**
	 * 类型搜索
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/searchCate",method = RequestMethod.GET)
	public SearchPageBean<Search> searchCate(@RequestParam(value = "sortField", required = true) String sortField,@RequestParam(value = "search", required = true) String search, @RequestParam(value = "curPage", required = true) int curPage,@RequestParam(value = "pageSize", required = true) int pageSize);
	/**
	 * 时间范围搜索
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/searchRange",method = RequestMethod.GET)
	public SearchPageBean<Search> searchRange(@RequestParam(value = "sortField", required = true) String sortField,@RequestParam(value = "startTime", required = true) Long startTime,@RequestParam(value = "endTime", required = true) Long endTime, @RequestParam(value = "curPage", required = true) int curPage,@RequestParam(value = "pageSize", required = true) int pageSize);
	/**
	 * 时间范围搜索
	 * @param searchDoc
	 * @param model
	 */
	@RequestMapping(value = "/search/searchConform",method = RequestMethod.GET)
	public List<Search> searchConform(@RequestParam(value = "sortField", required = true) String sortField,@RequestParam(value = "search", required = true) String search);
	@RequestMapping(value = "/search/searchGroup",method = RequestMethod.GET)
	public List<Search> searchGroup(@RequestParam(value = "sortField", required = true) String sortField,@RequestParam(value = "type", required = true) String type,@RequestParam(value = "indexCount", required = true) Integer indexCount);
}
