package utils;

import static utils.Library.*;

public class CoverMaster {
    public static void main(String[] args) {
        int i;
        int[] kSet = {0, 1, 2};
        print(kSet);
        for (i = 0; i < mSetsCount; i++) {
            if (mSets[i] != null && intersection(mSets[i], kSet) >= t) {
                mSets[i] = null;
            }
        }
        while (!isEmpty(mSets)) {
            kSet = getMostCommonNumbers(mSets, t);
            print(kSet);
            for (i = 0; i < mSetsCount; i++) {
                if (mSets[i] != null && intersection(mSets[i], kSet) >= t) {
                    mSets[i] = null;
                }
            }
        }
    }
}
