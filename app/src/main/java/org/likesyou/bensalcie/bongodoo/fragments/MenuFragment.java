package org.likesyou.bensalcie.bongodoo.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.likesyou.bensalcie.bongodoo.R;
import org.likesyou.bensalcie.bongodoo.common.Music;
import org.likesyou.bensalcie.bongodoo.common.Shared;
import org.likesyou.bensalcie.bongodoo.events.ui.StartEvent;
import org.likesyou.bensalcie.bongodoo.ui.PopupManager;
import org.likesyou.bensalcie.bongodoo.utils.Utils;

public class MenuFragment extends Fragment {

	private TextView mTitle;
	private ImageView mStartGameButton;
	private ImageView mStartButtonLights;
	private ImageView mTooltip;
	private ImageView mSettingsGameButton;
	private ImageView mGooglePlayGameButton;
	private TextView tvPrivacy,tvAbout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.menu_fragment, container, false);
		mTitle = view.findViewById(R.id.title);

		tvPrivacy=view.findViewById(R.id.privary_textview);
		tvAbout=view.findViewById(R.id.about_textview);

		tvPrivacy.getPaint().setUnderlineText(true);
		tvAbout.getPaint().setUnderlineText(true);
		tvPrivacy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String url="http://bensalcie.likesyou.org/privacypolicies/mastermind.html";
				Intent i=new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
		tvAbout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String url="http://bensalcie.likesyou.org";
				Intent i=new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
		Typeface typeface=Typeface.createFromAsset(getResources().getAssets(), "fonts/grobold.ttf");
		mTitle.setTypeface(typeface);
		mStartGameButton = (ImageView) view.findViewById(R.id.start_game_button);
		mSettingsGameButton = (ImageView) view.findViewById(R.id.settings_game_button);
		mSettingsGameButton.setSoundEffectsEnabled(false);
		mSettingsGameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				PopupManager.showPopupSettings();
			}
		});
		mGooglePlayGameButton = (ImageView) view.findViewById(R.id.google_play_button);

		mGooglePlayGameButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getActivity(), "Online matches will be available soon...", Toast.LENGTH_LONG).show();
			}
		});
		mStartButtonLights = (ImageView) view.findViewById(R.id.start_game_button_lights);
		mTooltip = (ImageView) view.findViewById(R.id.tooltip);
		mStartGameButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// animate title from place and navigation buttons from place
				animateAllAssetsOff(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						Shared.eventBus.notify(new StartEvent());
					}
				});
			}
		});

		startLightsAnimation();
		startTootipAnimation();

		// play background music
		Music.playBackgroundMusic();
		return view;
	}

	protected void animateAllAssetsOff(AnimatorListenerAdapter adapter) {
		// title
		// 120dp + 50dp + buffer(30dp)
		ObjectAnimator titleAnimator = ObjectAnimator.ofFloat(mTitle, "translationY", Utils.px(-200));
		titleAnimator.setInterpolator(new AccelerateInterpolator(2));
		titleAnimator.setDuration(300);

		// lights
		ObjectAnimator lightsAnimatorX = ObjectAnimator.ofFloat(mStartButtonLights, "scaleX", 0f);
		ObjectAnimator lightsAnimatorY = ObjectAnimator.ofFloat(mStartButtonLights, "scaleY", 0f);

		// tooltip
		ObjectAnimator tooltipAnimator = ObjectAnimator.ofFloat(mTooltip, "alpha", 0f);
		tooltipAnimator.setDuration(100);

		// settings button
		ObjectAnimator settingsAnimator = ObjectAnimator.ofFloat(mSettingsGameButton, "translationY", Utils.px(120));
		settingsAnimator.setInterpolator(new AccelerateInterpolator(2));
		settingsAnimator.setDuration(300);

		// google play button
		ObjectAnimator googlePlayAnimator = ObjectAnimator.ofFloat(mGooglePlayGameButton, "translationY", Utils.px(120));
		googlePlayAnimator.setInterpolator(new AccelerateInterpolator(2));
		googlePlayAnimator.setDuration(300);

		// start button
		ObjectAnimator startButtonAnimator = ObjectAnimator.ofFloat(mStartGameButton, "translationY", Utils.px(130));
		startButtonAnimator.setInterpolator(new AccelerateInterpolator(2));
		startButtonAnimator.setDuration(300);

		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.playTogether(titleAnimator, lightsAnimatorX, lightsAnimatorY, tooltipAnimator, settingsAnimator, googlePlayAnimator, startButtonAnimator);
		animatorSet.addListener(adapter);
		animatorSet.start();
	}

	private void startTootipAnimation() {
		ObjectAnimator scaleY = ObjectAnimator.ofFloat(mTooltip, "scaleY", 0.8f);
		scaleY.setDuration(200);
		ObjectAnimator scaleYBack = ObjectAnimator.ofFloat(mTooltip, "scaleY", 1f);
		scaleYBack.setDuration(500);
		scaleYBack.setInterpolator(new BounceInterpolator());
		final AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.setStartDelay(1000);
		animatorSet.playSequentially(scaleY, scaleYBack);
		animatorSet.addListener(new AnimatorListenerAdapter() {
			@Override
			public void onAnimationEnd(Animator animation) {
				animatorSet.setStartDelay(2000);
				animatorSet.start();
			}
		});
		mTooltip.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		animatorSet.start();
	}

	private void startLightsAnimation() {
		ObjectAnimator animator = ObjectAnimator.ofFloat(mStartButtonLights, "rotation", 0f, 360f);
		animator.setInterpolator(new AccelerateDecelerateInterpolator());
		animator.setDuration(6000);
		animator.setRepeatCount(ValueAnimator.INFINITE);
		mStartButtonLights.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		animator.start();
	}

}
