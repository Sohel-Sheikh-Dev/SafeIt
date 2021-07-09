package com.example.safeit.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.safeit.Fragments.AllItemFrag;
import com.example.safeit.Fragments.NotesFrag;
import com.example.safeit.Fragments.PasswordFrag;
import com.example.safeit.Fragments.PersonalInfoFrag;

public class FragmentAdapter extends FragmentStateAdapter {
    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
//            case 0:
//                AllItemFrag tab1 = new AllItemFrag();
//                return tab1;
            case 0:
                PasswordFrag tab2 = new PasswordFrag();
                return tab2;
            case 1:
                NotesFrag tab3 = new NotesFrag();
                return tab3;

            case 2:
                PersonalInfoFrag tab4 = new PersonalInfoFrag();
                return tab4;

            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

