package org.likesyou.bensalcie.bongodoo.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.likesyou.bensalcie.bongodoo.R;
import org.likesyou.bensalcie.bongodoo.common.Memory;
import org.likesyou.bensalcie.bongodoo.common.Shared;
import org.likesyou.bensalcie.bongodoo.events.ui.ThemeSelectedEvent;
import org.likesyou.bensalcie.bongodoo.themes.Theme;
import org.likesyou.bensalcie.bongodoo.themes.Themes;

import java.util.Locale;

public class ThemeSelectFragment extends Fragment {
	//private InterstitialAd interstitial;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = LayoutInflater.from(Shared.context).inflate(R.layout.theme_select_fragment, container, false);
		View animals = view.findViewById(R.id.theme_animals_container);
		View monsters = view.findViewById(R.id.theme_monsters_container);
		View emoji = view.findViewById(R.id.theme_emoji_container);
		MobileAds.initialize (getContext(), getString (R.string.admob_app_id));
		AdRequest adIRequest = new AdRequest.Builder().build();

		// Prepare the Interstitial Ad Activity
		//interstitial = new InterstitialAd(getContext());

		// Insert the Ad Unit ID
		//interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));

		// Interstitial Ad load Request
		//interstitial.loadAd(adIRequest);

		// Prepare an Interstitial Ad Listener
		/*interstitial.setAdListener(new AdListener()
		{
			public void onAdLoaded()
			{
				// Call displayInterstitial() function when the Ad loads
				displayInterstitial();
			}
		});*/



		final Theme themeAnimals = Themes.createAnimalsTheme();
		setStars((ImageView) animals.findViewById(R.id.theme_animals), themeAnimals, "animals");
		final Theme themeMonsters = Themes.createMosterTheme();
		setStars((ImageView) monsters.findViewById(R.id.theme_monsters), themeMonsters, "monsters");
		final Theme themeEmoji = Themes.createEmojiTheme();
		setStars((ImageView) emoji.findViewById(R.id.theme_emoji), themeEmoji, "emoji");

		animals.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Shared.eventBus.notify(new ThemeSelectedEvent(themeAnimals));
			}
		});

		monsters.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Shared.eventBus.notify(new ThemeSelectedEvent(themeMonsters));
			}
		});

		emoji.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Shared.eventBus.notify(new ThemeSelectedEvent(themeEmoji));
			}
		});

		/**
		 * Imporove performance first!!!
		 */
		animateShow(animals);
		animateShow(monsters);
		animateShow(emoji);

		return view;
	}



	private void animateShow(View view) {
		ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1f);
		ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1f);
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.setDuration(300);
		animatorSet.playTogether(animatorScaleX, animatorScaleY);
		animatorSet.setInterpolator(new DecelerateInterpolator(2));
		view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		animatorSet.start();
	}

	private void setStars(ImageView imageView, Theme theme, String type) {
		int sum = 0;
		for (int difficulty = 1; difficulty <= 6; difficulty++) {
			sum += Memory.getHighStars(theme.id, difficulty);
		}
		int num = sum / 6;
		if (num != 0) {
			String drawableResourceName = String.format(Locale.US, type + "_theme_star_%d", num);
			int drawableResourceId = Shared.context.getResources().getIdentifier(drawableResourceName, "drawable", Shared.context.getPackageName());
			imageView.setImageResource(drawableResourceId);
		}
	}
	/*public void displayInterstitial() {
		// If Interstitial Ads are loaded then show else show nothing.
		if (interstitial.isLoaded()) {
			interstitial.show();
		}
	}*/
	}


