package com.yoohoo.en.utils;

import com.yoohoo.en.dao.model.TClassDefine;
import com.yoohoo.en.dao.model.ext.StuTypeItem;

import java.util.ArrayList;
import java.util.List;

public class TypeItemUtils {

	public static List<StuTypeItem> getStuTypeItems() {
		List<StuTypeItem> datas=new ArrayList<>();
		datas.add(new StuTypeItem(1));//正常(外教VIP)
		datas.add(new StuTypeItem(2));//优惠(外教VIP)
		datas.add(new StuTypeItem(3));//平台费(外教VIP)
		datas.add(new StuTypeItem(4));//正常(中教VIP)
		datas.add(new StuTypeItem(5));//优惠(中教VIP)
		datas.add(new StuTypeItem(6));//平台费(中教VIP)

		datas.add(new StuTypeItem(7));//正常(外教V2)
		datas.add(new StuTypeItem(8));//优惠(外教V2)
		datas.add(new StuTypeItem(9));//平台费(外教V2)
		datas.add(new StuTypeItem(10));//正常(中教V2)
		datas.add(new StuTypeItem(11));//优惠(中教V2)
		datas.add(new StuTypeItem(12));//平台费(中教V2)

		datas.add(new StuTypeItem(13));//正常(外教V3)
		datas.add(new StuTypeItem(14));//优惠(外教V3)
		datas.add(new StuTypeItem(15));//平台费(外教V3)
		datas.add(new StuTypeItem(16));//正常(中教V3)
		datas.add(new StuTypeItem(17));//优惠(中教V3)
		datas.add(new StuTypeItem(18));//平台费(中教V3)

		datas.add(new StuTypeItem(19));//正常(外教V4)
		datas.add(new StuTypeItem(20));//优惠(外教V4)
		datas.add(new StuTypeItem(21));//平台费(外教V4)
		datas.add(new StuTypeItem(22));//正常(中教V4)
		datas.add(new StuTypeItem(23));//优惠(中教V4)
		datas.add(new StuTypeItem(24));//平台费(中教V4)

		datas.add(new StuTypeItem(25));//赠送



		return datas;
	}
	public static void buildStuTypeItemType(StuTypeItem consume) {
		if(null == consume.getType())
		{
			//(外教)正常
			consume.setType(1);
			consume.setTypeName("(外教)正常");
		}
		switch(consume.getType())
		{

			case 25:consume.setTypeName("赠送");break;

			case 24:consume.setTypeName("平台费(中教V4)");break;
			case 23:consume.setTypeName("优惠(中教V4)");break;
			case 22:consume.setTypeName("正常(中教V4)");break;
			case 21:consume.setTypeName("平台费(外教V4)");break;
			case 20:consume.setTypeName("优惠(外教V4)");break;
			case 19:consume.setTypeName("正常(外教V4)");break;

			case 18: consume.setTypeName("平台费(中教V3)");break;
			case 17: consume.setTypeName("优惠(中教V3)");break;
			case 16: consume.setTypeName("正常(中教V3)");break;
			case 15: consume.setTypeName("平台费(外教V3)");break;
			case 14: consume.setTypeName("优惠(外教V3)");break;
			case 13: consume.setTypeName("(正常(外教V3)");break;

			case 12: consume.setTypeName("平台费(中教V2)");break;
			case 11: consume.setTypeName("优惠(中教V2)");break;
		    case 10: consume.setTypeName("正常(中教V2)");break;
			case 9: consume.setTypeName("(平台费(外教V2)");break;
			case 8: consume.setTypeName("优惠(外教V2)");break;
			case 7: consume.setTypeName("正常(外教V2)");break;


			case 6: consume.setTypeName("平台费(中教VIP)");break;
			case 5: consume.setTypeName("优惠(中教VIP)");break;
			case 4: consume.setTypeName("正常(中教VIP)");break;
			case 3: consume.setTypeName("平台费(外教VIP)");break;
			case 2:consume.setTypeName("优惠(外教VIP)");break;
			case 1:consume.setTypeName("正常(外教VIP)");break;
		}
	}
	public static void buildStuTypeItemDefaultPrice(TClassDefine classItem, StuTypeItem consume) {
		if(null == consume.getPrice() || consume.getPrice() <= 0) 
		{
			switch(consume.getType())
			{
				//赠送
				case 25:consume.setPrice(classItem.getPrice21());break;
				//平台费（中教V4）
				case 24:consume.setPrice(classItem.getPrice20());break;
				//优惠（中教V4）
				case 23:consume.setPrice(classItem.getPrice19());break;
				//正常（中教V4）
				case 22:consume.setPrice(classItem.getPrice18());break;
				//平台费（外教V4）
				case 21:consume.setPrice(classItem.getPrice17());break;
				//优惠（外教V4）
				case 20:consume.setPrice(classItem.getPrice16());break;
				//正常（外教V4）
				case 19:consume.setPrice(classItem.getPrice15());break;
				//平台费（中教V3）
				case 18:consume.setPrice(classItem.getPrice14());break;
				//优惠（中教V3）
				case 17:consume.setPrice(classItem.getPrice13());break;
				//正常（中教V3
				case 16:consume.setPrice(classItem.getPrice12());break;
				//平台费（外教V3）
				case 15:consume.setPrice(classItem.getPrice11());break;
				//优惠（外教V3）
				case 14:consume.setPrice(classItem.getPrice10());break;
				//正常（外教V3）
				case 13:consume.setPrice(classItem.getPrice03());break;
				//平台费（中教V2）
				case 12:consume.setPrice(classItem.getPrice02());break;
				//优惠（中教V2）
				case 11:consume.setPrice(classItem.getPrice9());break;
				//正常（中教V2）
				case 10:consume.setPrice(classItem.getPrice8());break;
				//平台费（外教V2）
				case 9:consume.setPrice(classItem.getPrice7());break;
				//优惠（外教V2）
				case 8:consume.setPrice(classItem.getPrice6());break;
				//正常（外教V2）
				case 7:consume.setPrice(classItem.getPrice5());break;
				//平台费（中教VIP）
				case 6:consume.setPrice(classItem.getPrice01());break;
				//优惠（中教VIP）
				case 5:consume.setPrice(classItem.getAttendPrice());break;
				//正常（中教VIP）
				case 4:consume.setPrice(classItem.getTryPrice());break;
				//平台费（外教VIP）
				case 3:consume.setPrice(classItem.getLeavePrice());break;
				//优惠（外教）
				case 2:consume.setPrice(classItem.getAbsenteeismPrice());break;
				//正常（外教VIP）
				case 1:consume.setPrice(classItem.getLessonPrice());break;
			}
		}
	}
}
