package com.parse.starter.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.ViewGroup;

import com.parse.starter.R;
import com.parse.starter.fragments.HomeFragment;
import com.parse.starter.fragments.UsuariosFragment;

import java.util.HashMap;

public class TabsAdapter extends FragmentStatePagerAdapter{
    private Context context;
    //private String[] abas = new String[]{"Home","Contatos"};
    private int[] icons = new int[]{R.drawable.ic_action_home,R.drawable.ic_people};
    private int tamanhoIcone;
    private HashMap<Integer, Fragment> fragmentUtilizados = new HashMap<>();

    public TabsAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        double escala = this.context.getResources().getDisplayMetrics().density;
        tamanhoIcone = (int)(36 * escala);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        switch (position){

            case 0:
                fragment = new HomeFragment();
                fragmentUtilizados.put(position,fragment);
                break;
            case 1:
                fragment = new UsuariosFragment();
                break;
        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        fragmentUtilizados.remove(position);
    }

    public Fragment getFragment(Integer indice){

        return fragmentUtilizados.get(indice);

    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        Drawable drawable = ContextCompat.getDrawable(this.context,icons[position]);
        drawable.setBounds(0,0,tamanhoIcone,tamanhoIcone);

        ImageSpan imageSpan = new ImageSpan(drawable);

        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;
    }
}
