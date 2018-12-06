package com.java.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.java.config.YwymConfig;
import com.java.entity.YwymImg;
import com.java.sys.common.utils.Tool;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

import com.java.entity.YwymGoods;
import com.java.dao.YwymGoodsDao;
import com.java.sys.common.basic.service.BaseService;
import com.java.entity.response.ReturnGoods;

@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class YwymGoodsService extends BaseService<YwymGoodsDao, YwymGoods> {

	@Resource
	private YwymImgService imgService;
	
	public ReturnGoods getReturnGoods(YwymGoods entity){
		ReturnGoods goods = new ReturnGoods();
		if(entity != null){
			try{
				BeanUtils.copyProperties(goods, entity);
				goods.icon = Tool.toUrl(goods.getIcon());
				String status = goods.getIsShelve();
				//如果礼品是上架状态，但是库存达预警库存，则显示售罄
				if (YwymConfig.SONE.equals(status) && goods.getStock() - goods.getWarnStock() <= 0) {
					status ="2";
				}
				goods.status = status;
				YwymImg _i = new YwymImg();
				_i.setObjectId(goods.id);
				List<YwymImg> list = imgService.findList(_i);
				goods.imgList = imgService.getReturnImgList(list);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return goods;
	}
	
	public List<ReturnGoods> getReturnGoodsList(List<YwymGoods> entityList){
		List<ReturnGoods> list = new ArrayList<ReturnGoods>();
		if(entityList != null && entityList.size()>0){
			for(YwymGoods entity : entityList){
				list.add(getReturnGoods(entity));
			}
		}
		return list;
	}


	/**
	 *	减少礼品库存,增加兑换数量
	 * @param goodsId
	 * @param num
	 * @throws Exception
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public synchronized void numSub(String goodsId,int num) throws Exception{
		if(Tool.isNotBlank(goodsId)){
			if(num <= 0){
				throw new Exception("numSub() : num <= 0 ");
			}
			YwymGoods goods = get(goodsId);
			if(goods == null){
				throw new Exception("numSub() : goods == null ");
			}
			if(num > goods.getStock()-goods.getWarnStock()){
				throw new Exception("礼品库存不足！");
			}
			int result = goods.getStock()-num;
			goods.setStock(result);
			goods.setSaleNum(goods.getSaleNum()+1);
			save(goods);
		}
	}
}