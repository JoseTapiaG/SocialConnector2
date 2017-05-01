package cl.josemanuel.socialconnector2.database;

import android.provider.BaseColumns;

public final class SocialConnectorContract {
    private SocialConnectorContract() {}

    public static class Contact implements BaseColumns {
        public static final String TABLE_NAME = "contact";
        public static final String NAME = "name";
        public static final String EMAIL = "email";
        public static final String SKYPE = "skype";
        public static final String AVATAR = "avatar_id";
    }

    public static class Message implements BaseColumns {
        public static final String TABLE_NAME = "message";
        public static final String TEXT = "caption";
        public static final String DATE = "date";
        public static final String SEEN = "seen";
        public static final String CONTACT = "contact_id";
        public static final String PHOTO = "photo_id";
    }

    public static class Photo implements BaseColumns {
        public static final String TABLE_NAME = "photo";
        public static final String URL = "url";
        public static final String PATH = "path";
        public static final String SEEN = "seen";
        public static final String DATE = "date";
        public static final String CONTACT = "contact_id";
        public static final String MESSAGE = "message_id";
    }
}
