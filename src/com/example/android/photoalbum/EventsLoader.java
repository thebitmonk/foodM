

package com.example.android.photoalbum;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

class EventsLoader {
    private final Context mContext;

    EventsLoader(Context context) {
        mContext = context;
    }

    List<Events> loadEvents() {
        ArrayList<Events> events = new ArrayList<Events>();
        addEvent(events, "Agni : Rock Night", "12", "Sep", "2012", "9 PM", "rock + psychedelic",  "Agnee is an Indian rock band, and the first to have seen a mainstream release. The band's debut album Agnee was released on May 15, 2007. Since then, they have released many singles using the free distributable medium of the World Wide Web and gained a cult following in the rock music crazy circles of Mumbai and Pune.", "band.jpg", "band.mp3");
        addEvent(events, "Advaita", "15", "Sep", "2012", "10 PM", "pop",  "Advaita means experiencing all as One. To the uninitiated, Advaita is an eclectic fusion band based in New Delhi, India. It is the expression of eight distinct musical sensibilities that dissolve into each other to bring forth a truly unique and ingenious sound. Indian Classical Traditions are given new dimension, as they are reinvented within a contemporary soundscape in harmony with Rock and Electronica. Their music is deeply inspired by the quest to discover rhythms and textures that resonate around us and within us. The Sarangi, Tabla, Bass, Keyboards, Guitars and Drums blend seamlessly, complimented perfectly by two of the most gifted Hindustani and Western Vocalists. Their brand of contemporary organic fusion has been lauded as one of the most original and ground- breaking sounds to ever come out of the Indian underground music scene. They have successfully carved out a niche.. for their New Age Music in the Global context, while staying true to their characteristically Indian roots. The Advaita Experience is defined by the pursuit of that incredible moment during performance when the musician becomes the music and forgets his own identity, resulting in thoroughly electrifying stage performances. Since their inception in 2004, Advaita has been established as one of the most thrilling, innovative and original acts on the Indian music scene, with the unique reputation of enthralling audiences in any kind of setting", "band_1.jpg", "band.mp3");
        addEvent(events, "Agni : Rock Night", "12", "Sep", "2012", "9 PM", "rock + psychedelic",  "Agnee is an Indian rock band, and the first to have seen a mainstream release. The band's debut album Agnee was released on May 15, 2007. Since then, they have released many singles using the free distributable medium of the World Wide Web and gained a cult following in the rock music crazy circles of Mumbai and Pune.", "band.jpg", "band.mp3");
        addEvent(events, "Agni : Rock Night", "12", "Sep", "2012", "9 PM", "rock + psychedelic",  "Agnee is an Indian rock band, and the first to have seen a mainstream release. The band's debut album Agnee was released on May 15, 2007. Since then, they have released many singles using the free distributable medium of the World Wide Web and gained a cult following in the rock music crazy circles of Mumbai and Pune.", "band.jpg", "band.mp3");
        return events;
    }

    private void addEvent(ArrayList<Events> events, String name, String day, String month,
            String year, String timing, String tags, String description, String image_link,
            String audio_link) {
        Events event = new Events();
    	event.name = name;
    	event.day = day;
    	event.month = month;
    	event.year = year;
    	event.timing = timing;
    	event.tags = tags;
    	event.description = description;
    	event.image_link = image_link;
    	event.audio_link = audio_link;
    	events.add(event);
    }
}
