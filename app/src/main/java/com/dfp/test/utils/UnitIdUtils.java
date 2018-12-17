
package com.dfp.test.utils;

import com.dfp.test.ads.AdType;

/**
 * This class provides unit ids in cyclic way(ie. mrecIds[0], footerIds[0], headerIds[0], mrecIds[1], footerIds[1], headerIds[1])
 */
public class UnitIdUtils {
    private int headerCounter = 0;
    private int footerCounter = 0;
    private int mrecCounter = 0;
    private int adtypecounter = 0;

    String[] mrecIds = {
            "/7176/TOI_App_Android/TOI_App_AOS_Election/TOI_App_AOS_ELE_PT_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_Business/TOI_App_AOS_BUS_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_Home/TOI_App_AOS_HP_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_Business/TOI_App_AOS_BUS_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_Sports/TOI_App_AOS_SPR_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_City/TOI_App_AOS_CTY_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_Business/TOI_App_AOS_BUS_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_TV/TOI_App_AOS_TV_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_World/TOI_App_AOS_WRD_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_TV/TOI_App_AOS_TV_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_Science/TOI_App_AOS_SCI_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_World/TOI_App_AOS_WRD_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_Mrec",
            "/7176/TOI_App_Android/TOI_App_AOS_Business/TOI_App_AOS_BUS_Mrec"
    };
    String[] footerIds = {
            "/7176/TOI_App_Android/TOI_App_AOS_Latest/TOI_App_AOS_LTST_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_Entertainment/TOI_App_AOS_ENT_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_Home/TOI_App_AOS_HP_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_Latest/TOI_App_AOS_LTST_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_Home/TOI_App_AOS_HP_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_Home/TOI_App_AOS_HP_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_Home/TOI_App_AOS_HP_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_Election/TOI_App_AOS_ELE_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_Brief/TOI_App_AOS_Brf_BTF",
            "/7176/TOI_App_Test/TOI_App_Test_BTF1",
            "/7176/TOI_App_Android/TOI_App_AOS_Photos/TOI_App_AOS_PHT_PT_BTF",
            "/7176/GadgetsNow_App_Android/GadgetsNow_App_AOS_ROS/GadgetsNow_App_AOS_ROS_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_Trending/TOI_App_AOS_TRD_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_Sports/TOI_App_AOS_SPR_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_City/TOI_App_AOS_CTY_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_Business/TOI_App_AOS_BUS_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_TV/TOI_App_AOS_TV_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_World/TOI_App_AOS_WRD_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_TV/TOI_App_AOS_TV_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_Science/TOI_App_AOS_SCI_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_World/TOI_App_AOS_WRD_PT_BTF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_BTF"};

    String[] headerIds = {
            "/7176/TOI_App_Android/TOI_App_AOS_Home/TOI_App_AOS_HP_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_Home/TOI_App_AOS_HP_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_Trending/TOI_App_AOS_TRD_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_Business/TOI_App_AOS_BUS_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_Latest/TOI_App_AOS_LTST_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_Latest/TOI_App_AOS_LTST_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_Business/TOI_App_AOS_BUS_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_Sports/TOI_App_AOS_SPR_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_City/TOI_App_AOS_CTY_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_Business/TOI_App_AOS_BUS_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_TV/TOI_App_AOS_TV_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_World/TOI_App_AOS_WRD_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_TV/TOI_App_AOS_TV_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_Science/TOI_App_AOS_SCI_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_World/TOI_App_AOS_WRD_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_India/TOI_App_AOS_IND_PT_ATF",
            "/7176/TOI_App_Android/TOI_App_AOS_Business/TOI_App_AOS_BUS_PT_ATF"};

    /**
     * provide next unitid
     *
     * @return
     */
    public UnitIdItem getNextUnitId() {
        adtypecounter = adtypecounter++ % 3;
        switch (adtypecounter) {
            case 0://MREC
                return new UnitIdItem(mrecIds[mrecCounter++ % mrecIds.length], AdType.MREC);
            case 1://Footer
                return new UnitIdItem(footerIds[footerCounter++ % footerIds.length], AdType.FOOTER);
            case 2://Header
                return new UnitIdItem(headerIds[headerCounter++ % headerIds.length], AdType.HEADER);
        }
        return null;
    }

    public class UnitIdItem {
        String unitId;
        int adType;

        public UnitIdItem(String unitId, @AdType int adType) {
            this.unitId = unitId;
            this.adType = adType;
        }

        public String getUnitId() {
            return unitId;
        }

        public int getAdType() {
            return adType;
        }
    }
}
