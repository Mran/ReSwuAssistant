package org.swuos.swuassistant.weight;

import android.view.View;
import android.widget.ImageView;

import org.swuos.swuassistant.R;
import org.swuos.swuassistant.commmon.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mran on 17-12-10.
 */

public class BottomBar {
    private List<View> mViewList;
    private BottomBarItemClickLinstener mBottomBarItemClickLinstener;

    public BottomBar(View view) {
        mViewList = new ArrayList<>();
        mViewList.add(view.findViewById(R.id.bottom_icon_schedule));
        mViewList.add(view.findViewById(R.id.bottom_icon_grade));
        mViewList.add(view.findViewById(R.id.bottom_icon_lib));
        mViewList.add(view.findViewById(R.id.bottom_icon_mine));
        for (int i = 0; i < mViewList.size(); i++) {
            View iconView = mViewList.get(i);
            iconView.setTag(i);
            iconView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int state[] = {android.R.attr.state_selected * (v.isSelected() ? -1 : 1)};
                    v.setSelected(!v.isSelected());
                    ((ImageView) v).setImageState(state, true);
                    Logger.d("BottomBar", "onClick: v" + v.isSelected());
                    mBottomBarItemClickLinstener.itemClick((Integer) v.getTag());
                    cleanOtherItemState((Integer) v.getTag());
                }
            });
        }
    }
public  void setSelectedItem(int index)
{
    View v=mViewList.get(index);
    int state[] = {android.R.attr.state_selected * (v.isSelected() ? -1 : 1)};
    v.setSelected(!v.isSelected());
    ((ImageView) v).setImageState(state, true);
    cleanOtherItemState((Integer) v.getTag());
}
    private void cleanOtherItemState(int i) {
        for (int j = 0; j < mViewList.size(); j++) {
            if (j == i)
                continue;
            View v = mViewList.get(j);
            int state[] = {android.R.attr.state_selected * -1};
            v.setSelected(false);
            ((ImageView) v).setImageState(state, true);
        }
    }

    public void setBottomBarItemClickLinstener(BottomBarItemClickLinstener bottomBarItemClickLinstener) {
        this.mBottomBarItemClickLinstener = bottomBarItemClickLinstener;
    }

    public interface BottomBarItemClickLinstener {
        void itemClick(int i);

    }
}
