package com.yahoo.android_alikuigphoto;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by aliku on 2015/7/29.
 */
public class IGPhotoAdapter extends ArrayAdapter<IGPhoto> {

    public IGPhotoAdapter(Context context, List<IGPhoto> objects) {
        super(context, android.R.layout.simple_expandable_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        IGPhoto photo = getItem(position);
        // Check if we are using a recycled view, if not we need to inflate
        if (convertView == null) {
            // create a new view from template
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }
        // Lookup the views for populating the data (image, caption)
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        TextView tvUser = (TextView) convertView.findViewById(R.id.tvUser);
        TextView tvLikeCount = (TextView) convertView.findViewById(R.id.tvLikeCount);
        TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        ImageView ivIcon = (ImageView) convertView.findViewById(R.id.ivIcon);
        // Insert the model data into each of the view items
        String sCreator = getContext().getString(R.string.creator);
        String sLikes = getContext().getString(R.string.likes);

        tvCaption.setText(photo.caption);
        tvUser.setText(String.format(sCreator, photo.username));
        tvLikeCount.setText(String.format(sLikes, photo.likesCount));
        tvTime.setText(DateUtils.getRelativeTimeSpanString(photo.createTime * 1000, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS));
        // clear out the imageview
        ivPhoto.setImageResource(0);
        ivIcon.setImageResource(1);
        // Insert the image using picasso
        Picasso.with(getContext()).load(photo.imageURL).into(ivPhoto);
        Picasso.with(getContext()).load(photo.userIconURL).into(ivIcon);
        // Return the created item as a view
        return convertView;
    }
}
