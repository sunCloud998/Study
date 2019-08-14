package com.sfy.java.guava.hash;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.hash.Hashing;
import org.junit.Test;

import java.util.List;

/**
 * @function:
 * @description: HashTest.java
 * @date: 2019/03/20
 * @author: sunfayun
 * @version: 1.0
 */
public class HashTest {

    @Test
    public void test01() {
        for (String source : this.sourceDataForHash()) {
            int hashCode = Hashing.murmur3_32().newHasher().putString(source, Charsets.UTF_8).hash().asInt();
//            System.err.println((hashCode  & 0x7fffffff) % 100);
            System.err.println(hashCode  & 0x7fffffff);
        }
    }

    private List<String> sourceDataForHash() {
        String source = "MT5521812000008,MT5521812000163,MT5521812000164,MT5521903000013,MT5521903000015,MT5521903000021,Q7NL00023944,Q7NL00023948,Q7NL00222118,yaceshanchu_5_ME50N_100000748,yaceshanchu_5_ME50N_101595998,yaceshanchu_5_ME50N_102403524,yaceshanchu_5_ME50N_1035387,yaceshanchu_5_ME50N_104217139,yaceshanchu_5_ME50N_104993091,yaceshanchu_5_ME50N_110275486,yaceshanchu_5_ME50N_113620655,yaceshanchu_5_ME50N_113655245,yaceshanchu_5_ME50N_113894656,yaceshanchu_5_ME50N_116030517,yaceshanchu_5_ME50N_116131420,yaceshanchu_5_ME50N_116914834,yaceshanchu_5_ME50N_117407071,yaceshanchu_5_ME50N_117679500,yaceshanchu_5_ME50N_117804306,yaceshanchu_5_ME50N_118062525,yaceshanchu_5_ME50N_118494902,yaceshanchu_5_ME50N_1220129,yaceshanchu_5_ME50N_2343224,yaceshanchu_5_ME50N_2343225,yaceshanchu_5_ME50N_2343406,yaceshanchu_5_ME50N_40897144,yaceshanchu_5_ME50N_40903909,yaceshanchu_5_ME50N_42066639,yaceshanchu_5_ME50N_42142593,yaceshanchu_5_ME50N_42959534,yaceshanchu_5_ME50N_4811573,yaceshanchu_5_ME50N_50124648,yaceshanchu_5_ME50N_5053372,yaceshanchu_5_ME50N_50995124,yaceshanchu_5_ME50N_51851725,yaceshanchu_5_ME50N_51957468,yaceshanchu_5_ME50N_51989217,yaceshanchu_5_ME50N_52101527,yaceshanchu_5_ME50N_5407909,yaceshanchu_5_ME50N_60005020,yaceshanchu_5_ME50N_60017949,yaceshanchu_5_ME50N_60029803,yaceshanchu_5_ME50N_60073706,yaceshanchu_5_ME50N_60245389,yaceshanchu_5_ME50N_60255777,yaceshanchu_5_ME50N_60293745,yaceshanchu_5_ME50N_60396649,yaceshanchu_5_ME50N_61479011,yaceshanchu_5_ME50N_62212340,yaceshanchu_5_ME50N_65112897,yaceshanchu_5_ME50N_65309040,yaceshanchu_5_ME50N_65547817,yaceshanchu_5_ME50N_66175063,yaceshanchu_5_ME50N_67009352,yaceshanchu_5_ME50N_72147212,yaceshanchu_5_ME50N_73214825,yaceshanchu_5_ME50N_73539773,yaceshanchu_5_ME50N_74608490,yaceshanchu_5_ME50N_77249387,yaceshanchu_5_ME50N_77565883,yaceshanchu_5_ME50N_78316001,yaceshanchu_5_ME50N_79009650,yaceshanchu_5_ME50N_79329182,yaceshanchu_5_ME50N_80003323,yaceshanchu_5_ME50N_81278767,yaceshanchu_5_ME50N_81704161,yaceshanchu_5_ME50N_82028011,yaceshanchu_5_ME50N_82115743,yaceshanchu_5_ME50N_82651259,yaceshanchu_5_ME50N_82674362,yaceshanchu_5_ME50N_82757447,yaceshanchu_5_ME50N_86398181,yaceshanchu_5_ME50N_86685972,yaceshanchu_5_ME50N_87056790,yaceshanchu_5_ME50N_87653632,yaceshanchu_5_ME50N_88796609,yaceshanchu_5_ME50N_90148,yaceshanchu_5_ME50N_90369590,yaceshanchu_5_ME50N_90769678,yaceshanchu_5_ME50N_91075861,yaceshanchu_5_ME50N_92248083,yaceshanchu_5_ME50N_92475866,yaceshanchu_5_ME50N_92808557,yaceshanchu_5_ME50N_92871842,yaceshanchu_5_ME50N_93242935,yaceshanchu_5_ME50N_93445746,yaceshanchu_5_ME50N_93873403,yaceshanchu_5_ME50N_93895885,yaceshanchu_5_ME50N_94546104,yaceshanchu_5_ME50N_94647152,yaceshanchu_5_ME50N_94737338,yaceshanchu_5_ME50N_94740298,yaceshanchu_5_ME50N_95769955,yaceshanchu_5_ME50N_96092678,yaceshanchu_5_ME50N_96679336,yaceshanchu_5_ME50N_96717509,yaceshanchu_5_ME50N_96809990,yaceshanchu_5_ME50N_96898264,yaceshanchu_5_ME50N_96909774,yaceshanchu_5_ME50N_97484326,yaceshanchu_5_ME50N_97549495,yaceshanchu_5_ME50N_97755906,yaceshanchu_5_ME50N_97784698,yaceshanchu_5_ME50N_98206834,yaceshanchu_5_ME50N_98379474,yaceshanchu_5_ME50N_99127979,yaceshanchu_5_ME50N_99182270,yaceshanchu_5_ME50N_99684441,yaceshanchu_5_ME50N_99929268";
        return Splitter.on(",").splitToList(source);
    }

    public static int getHashValue(String deviceNo) {
        int hashCode = Hashing.murmur3_32().newHasher().putString(deviceNo, Charsets.UTF_8).hash().asInt();
        return hashCode  & 0x7fffffff;
    }

}
