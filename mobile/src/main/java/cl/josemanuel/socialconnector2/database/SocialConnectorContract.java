package cl.josemanuel.socialconnector2.database;

import android.provider.BaseColumns;

public final class SocialConnectorContract {
    private SocialConnectorContract() {
    }

    public static class Contact implements BaseColumns {
        public static final String TABLE_NAME = "contact";
        public static final String NAME = "name";
        public static final String EMAIL = "email";
        public static final String SKYPE = "skype";
        public static final String AVATAR = "avatar_id";

        public static final String SELECT_ALL =
                TABLE_NAME + "." + _ID + " as " + TABLE_NAME + "_" + _ID + ", " +
                        TABLE_NAME + "." + NAME + " as " + TABLE_NAME + "_" + NAME + ", " +
                        TABLE_NAME + "." + EMAIL + " as " + TABLE_NAME + "_" + EMAIL + ", " +
                        TABLE_NAME + "." + AVATAR + " as " + TABLE_NAME + "_" + AVATAR + ", " +
                        TABLE_NAME + "." + SKYPE + " as " + TABLE_NAME + "_" + SKYPE;
    }

    public static class Message implements BaseColumns {
        public static final String TABLE_NAME = "message";
        public static final String TEXT = "caption";
        public static final String DATE = "date";
        public static final String SEEN = "seen";
        public static final String CONTACT = "contact_id";
        public static final String PHOTO = "photo_id";

        public static final String SELECT_ALL =
                TABLE_NAME + "." + _ID + " as " + TABLE_NAME + "_" + _ID + ", " +
                        TABLE_NAME + "." + TEXT + " as " + TABLE_NAME + "_" + TEXT + ", " +
                        TABLE_NAME + "." + DATE + " as " + TABLE_NAME + "_" + DATE + ", " +
                        TABLE_NAME + "." + CONTACT + " as " + TABLE_NAME + "_" + CONTACT + ", " +
                        TABLE_NAME + "." + PHOTO + " as " + TABLE_NAME + "_" + PHOTO + ", " +
                        TABLE_NAME + "." + SEEN + " as " + TABLE_NAME + "_" + SEEN;
    }

    public static class Photo implements BaseColumns {
        public static final String TABLE_NAME = "photo";
        public static final String URL = "url";
        public static final String PATH = "path";
        public static final String SEEN = "seen";
        public static final String DATE = "date";
        public static final String CONTACT = "contact_id";
        public static final String MESSAGE = "message_id";

        public static final String SELECT_ALL =
                TABLE_NAME + "." + _ID + " as " + TABLE_NAME + "_" + _ID + ", " +
                        TABLE_NAME + "." + URL + " as " + TABLE_NAME + "_" + URL + ", " +
                        TABLE_NAME + "." + PATH + " as " + TABLE_NAME + "_" + PATH + ", " +
                        TABLE_NAME + "." + SEEN + " as " + TABLE_NAME + "_" + SEEN + ", " +
                        TABLE_NAME + "." + CONTACT + " as " + TABLE_NAME + "_" + CONTACT + ", " +
                        TABLE_NAME + "." + MESSAGE + " as " + TABLE_NAME + "_" + MESSAGE + ", " +
                        TABLE_NAME + "." + DATE + " as " + TABLE_NAME + "_" + DATE;
    }
}
