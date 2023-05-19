package capstone.fullstack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RevenueDto {

    @JsonProperty("row")
    private RowEntity row;
    @JsonProperty("RESULT")
    private ResultEntity result;
    @JsonProperty("list_total_count")
    private int listTotalCount;
    public static class RowEntity {
        @JsonProperty("STOR_CO")
        private String storCo;
        @JsonProperty("AGRDE_60_ABOVE_SELNG_CO")
        private String agrde60AboveSelngCo;
        @JsonProperty("AGRDE_50_SELNG_CO")
        private String agrde50SelngCo;
        @JsonProperty("AGRDE_40_SELNG_CO")
        private String agrde40SelngCo;
        @JsonProperty("AGRDE_30_SELNG_CO")
        private String agrde30SelngCo;
        @JsonProperty("AGRDE_20_SELNG_CO")
        private String agrde20SelngCo;
        @JsonProperty("AGRDE_10_SELNG_CO")
        private String agrde10SelngCo;
        @JsonProperty("FML_SELNG_CO")
        private String fmlSelngCo;
        @JsonProperty("ML_SELNG_CO")
        private String mlSelngCo;
        @JsonProperty("TMZON_21_24_SELNG_CO")
        private String tmzon2124SelngCo;
        @JsonProperty("TMZON_17_21_SELNG_CO")
        private String tmzon1721SelngCo;
        @JsonProperty("TMZON_14_17_SELNG_CO")
        private String tmzon1417SelngCo;
        @JsonProperty("TMZON_11_14_SELNG_CO")
        private String tmzon1114SelngCo;
        @JsonProperty("TMZON_06_11_SELNG_CO")
        private String tmzon0611SelngCo;
        @JsonProperty("TMZON_00_06_SELNG_CO")
        private String tmzon0006SelngCo;
        @JsonProperty("SUN_SELNG_CO")
        private String sunSelngCo;
        @JsonProperty("SAT_SELNG_CO")
        private String satSelngCo;
        @JsonProperty("FRI_SELNG_CO")
        private String friSelngCo;
        @JsonProperty("THUR_SELNG_CO")
        private String thurSelngCo;
        @JsonProperty("WED_SELNG_CO")
        private String wedSelngCo;
        @JsonProperty("TUES_SELNG_CO")
        private String tuesSelngCo;
        @JsonProperty("MON_SELNG_CO")
        private String monSelngCo;
        @JsonProperty("WKEND_SELNG_CO")
        private String wkendSelngCo;
        @JsonProperty("MDWK_SELNG_CO")
        private String mdwkSelngCo;
        @JsonProperty("AGRDE_60_ABOVE_SELNG_AMT")
        private String agrde60AboveSelngAmt;
        @JsonProperty("AGRDE_50_SELNG_AMT")
        private String agrde50SelngAmt;
        @JsonProperty("AGRDE_40_SELNG_AMT")
        private String agrde40SelngAmt;
        @JsonProperty("AGRDE_30_SELNG_AMT")
        private String agrde30SelngAmt;
        @JsonProperty("AGRDE_20_SELNG_AMT")
        private String agrde20SelngAmt;
        @JsonProperty("AGRDE_10_SELNG_AMT")
        private String agrde10SelngAmt;
        @JsonProperty("FML_SELNG_AMT")
        private String fmlSelngAmt;
        @JsonProperty("ML_SELNG_AMT")
        private String mlSelngAmt;
        @JsonProperty("TMZON_21_24_SELNG_AMT")
        private String tmzon2124SelngAmt;
        @JsonProperty("TMZON_17_21_SELNG_AMT")
        private String tmzon1721SelngAmt;
        @JsonProperty("TMZON_14_17_SELNG_AMT")
        private String tmzon1417SelngAmt;
        @JsonProperty("TMZON_11_14_SELNG_AMT")
        private String tmzon1114SelngAmt;
        @JsonProperty("TMZON_06_11_SELNG_AMT")
        private String tmzon0611SelngAmt;
        @JsonProperty("TMZON_00_06_SELNG_AMT")
        private String tmzon0006SelngAmt;
        @JsonProperty("SUN_SELNG_AMT")
        private String sunSelngAmt;
        @JsonProperty("SAT_SELNG_AMT")
        private String satSelngAmt;
        @JsonProperty("FRI_SELNG_AMT")
        private String friSelngAmt;
        @JsonProperty("THUR_SELNG_AMT")
        private String thurSelngAmt;
        @JsonProperty("WED_SELNG_AMT")
        private String wedSelngAmt;
        @JsonProperty("TUES_SELNG_AMT")
        private String tuesSelngAmt;
        @JsonProperty("MON_SELNG_AMT")
        private String monSelngAmt;
        @JsonProperty("WKEND_SELNG_AMT")
        private String wkendSelngAmt;
        @JsonProperty("MDWK_SELNG_AMT")
        private String mdwkSelngAmt;
        @JsonProperty("AGRDE_60_ABOVE_SELNG_RATE")
        private String agrde60AboveSelngRate;
        @JsonProperty("AGRDE_50_SELNG_RATE")
        private String agrde50SelngRate;
        @JsonProperty("AGRDE_40_SELNG_RATE")
        private String agrde40SelngRate;
        @JsonProperty("AGRDE_30_SELNG_RATE")
        private String agrde30SelngRate;
        @JsonProperty("AGRDE_20_SELNG_RATE")
        private String agrde20SelngRate;
        @JsonProperty("AGRDE_10_SELNG_RATE")
        private String agrde10SelngRate;
        @JsonProperty("FML_SELNG_RATE")
        private String fmlSelngRate;
        @JsonProperty("ML_SELNG_RATE")
        private String mlSelngRate;
        @JsonProperty("TMZON_21_24_SELNG_RATE")
        private String tmzon2124SelngRate;
        @JsonProperty("TMZON_17_21_SELNG_RATE")
        private String tmzon1721SelngRate;
        @JsonProperty("TMZON_14_17_SELNG_RATE")
        private String tmzon1417SelngRate;
        @JsonProperty("TMZON_11_14_SELNG_RATE")
        private String tmzon1114SelngRate;
        @JsonProperty("TMZON_06_11_SELNG_RATE")
        private String tmzon0611SelngRate;
        @JsonProperty("TMZON_00_06_SELNG_RATE")
        private String tmzon0006SelngRate;
        @JsonProperty("SUN_SELNG_RATE")
        private String sunSelngRate;
        @JsonProperty("SAT_SELNG_RATE")
        private String satSelngRate;
        @JsonProperty("FRI_SELNG_RATE")
        private String friSelngRate;
        @JsonProperty("THUR_SELNG_RATE")
        private String thurSelngRate;
        @JsonProperty("WED_SELNG_RATE")
        private String wedSelngRate;
        @JsonProperty("TUES_SELNG_RATE")
        private String tuesSelngRate;
        @JsonProperty("MON_SELNG_RATE")
        private String monSelngRate;
        @JsonProperty("WKEND_SELNG_RATE")
        private String wkendSelngRate;
        @JsonProperty("MDWK_SELNG_RATE")
        private String mdwkSelngRate;
        @JsonProperty("THSMON_SELNG_CO")
        private String thsmonSelngCo;
        @JsonProperty("THSMON_SELNG_AMT")
        private String thsmonSelngAmt;
        @JsonProperty("SVC_INDUTY_CD_NM")
        private String svcIndutyCdNm;
        @JsonProperty("SVC_INDUTY_CD")
        private String svcIndutyCd;
        @JsonProperty("TRDAR_CD_NM")
        private String trdarCdNm;
        @JsonProperty("TRDAR_CD")
        private String trdarCd;
        @JsonProperty("TRDAR_SE_CD_NM")
        private String trdarSeCdNm;
        @JsonProperty("TRDAR_SE_CD")
        private String trdarSeCd;
        @JsonProperty("STDR_QU_CD")
        private String stdrQuCd;
        @JsonProperty("STDR_YY_CD")
        private String stdrYyCd;
    }

    public static class ResultEntity {
        @JsonProperty("MESSAGE")
        private String message;
        @JsonProperty("CODE")
        private String code;
    }
}
