package com.java.sys.controller.ajax;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.java.sys.common.utils.Tool;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.sys.common.basic.controller.BaseController;
import com.java.sys.common.basic.result.BaseResult;
import com.java.sys.common.cache.CacheUtil;
import com.java.sys.common.tag.SysTree;
import com.java.sys.entity.SysMenu;
import com.java.sys.service.SysMenuService;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@Controller
@RequestMapping("/ajax/sysMenuAjaxController")
public class MenuAjaxController extends BaseController{
	@Resource
	private SysMenuService menuService;
	
	//管理后台登录成功后顶部以及左侧的菜单ajax查询
	/*@RequestMapping(value = "/findAllList", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> findAllList(HttpServletRequest request){
		List<SysMenu> list = CacheUtil.getMenuList();
		*//*List<SysMenu> list = menuService.findListOrderByLevel();*//*
		if(list != null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				if(!menuService.hasViewPerm(list.get(i))){
					list.remove(i);
					i -= 1;
				}
			}
		}
		return buildSuccessInfo(list);
	}*/

	@RequestMapping(value = "/findAllList", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> findAllList(HttpServletRequest request) {
		List<SysMenu> list = menuService.findListOrderByLevel();// 一级菜单
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {// 遍历一级菜单
				SysMenu firMenu = list.get(i);
				if (!menuService.hasViewPerm(firMenu)) {
					list.remove(i);
					i -= 1;
				}
				firMenu.setIcon(Tool.toUrl(firMenu.getIcon()));
				List<SysMenu> list1 = firMenu.getChildList();
				if (list1 != null && list1.size() > 0) {
					for (int j = 0; j < list1.size(); j++) {
						SysMenu secMenu = list1.get(j);
						if (!menuService.hasViewPerm(secMenu)) {
							list1.remove(j);
							j -= 1;
						}
						secMenu.setIcon(Tool.toUrl(secMenu.getIcon()));
						List<SysMenu> list2 = secMenu.getChildList();
						if (list2 != null && list2.size() > 0) {
							for (int k = 0; k < list2.size(); k++) {
								SysMenu thrMenu = list2.get(k);
								if (!menuService.hasViewPerm(thrMenu)) {
									list2.remove(k);
									k -= 1;
								}
								thrMenu.setIcon(Tool.toUrl(thrMenu.getIcon()));
							}
						}
					}
				}
			}
		}
		return buildSuccessInfo(list);
	}
	
	//zTree菜单查询
	@RequestMapping(value = "/findListTree", method = RequestMethod.POST)
	public ResponseEntity<BaseResult> findListTree(HttpServletRequest request){
		List<SysTree> treeList = CacheUtil.getMenuTree();
		/*List<SysTree> treeList = new ArrayList<SysTree>();
		SysMenu _m = new SysMenu();
		_m.setOrderBy("a.rank ASC");
		_m.setLevel("1");
		_m.setHide("0");
		List<SysMenu> list = menuService.findList(_m);
		if(list != null && list.size()>0){
			for(SysMenu menuOne : list){
				SysTree tree = new SysTree(menuOne.getId(), menuOne.getName());
				_m.setLevel("2");
				_m.setParentId(menuOne.getId());
				List<SysMenu> menuTwoList = menuService.findList(_m);
				if(menuTwoList != null && menuTwoList.size()>0){
					List<SysTree> treeList2 = new ArrayList<SysTree>();
					for(SysMenu menuTwo :menuTwoList){
						SysTree tree2 = new SysTree(menuTwo.getId(), menuTwo.getName());
						treeList2.add(tree2);
						_m.setLevel("3");
						_m.setParentId(menuTwo.getId());
						List<SysMenu> menuThreeList = menuService.findList(_m);
						if(menuThreeList != null && menuThreeList.size()>0){
							List<SysTree> treeList3 = new ArrayList<SysTree>();
							for(SysMenu menuThree : menuThreeList){
								SysTree tree3 = new SysTree(menuThree.getId(), menuThree.getName());
								treeList3.add(tree3);
							}
							tree2.setChildren(treeList3);
						}
					}
					tree.setChildren(treeList2);
				}
				treeList.add(tree);
			}
		}*/
		return buildSuccessInfo(treeList);
	}
	
	
	
}
