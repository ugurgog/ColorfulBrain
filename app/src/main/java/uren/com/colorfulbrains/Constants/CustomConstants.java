package uren.com.colorfulbrains.Constants;

public class CustomConstants {

    public static final String APP_NAME = "Colorful Brains";

    //Group Request Types
    public static final String LOGIN_USER = "loginUser";
    public static final String APP_GOOGLE_PLAY_DEFAULT_LINK = "https://play.google.com/store/apps/details?id=";
    public static final String APP_PACKAGE_NAME = "uren.com.colorfulbrains";
    public static final String APP_FB_URL = "https://colorful-intelligence.firebaseio.com/";

    //Character constants
    public static final String CHAR_AMPERSAND = "@";
    public static final String CHAR_COLON = ":";
    public static final String CHAR_HYPHEN = "-";
    public static final String CHAR_E = "E";
    public static final String CHAR_H = "H";

    public static final String GROUP_OP_CHOOSE_TYPE = "CHOOSE";
    public static final String GROUP_OP_VIEW_TYPE = "VIEW";

    public static final String CAMERA_TEXT = "CAMERA";
    public static final String GALLERY_TEXT = "GALLERY";
    public static final String FROM_FILE_TEXT = "FROM_FILE";

    public static final String LOGIN_METHOD_GOOGLE = "google";
    public static final String LOGIN_METHOD_NORMAL = "normal";

    public static final String fb_child_users = "Users";
    public static final String fb_child_name = "name";
    public static final String fb_child_userid = "userid";
    public static final String fb_child_username = "username";
    public static final String fb_child_profilePhotoUrl = "profilePhotoUrl";
    public static final String fb_child_email = "email";
    public static final String fb_child_phone = "Phone";
    public static final String fb_child_networks = "Networks";
    public static final String fb_child_groups = "Groups";
    public static final String fb_child_friends = "Friends";
    public static final String fb_child_countryCode = "countryCode";
    public static final String fb_child_dialCode = "dialCode";
    public static final String fb_child_phoneNumber = "phoneNumber";
    public static final String fb_child_whocompletedid = "whoCompletedId";
    public static final String fb_child_urgency = "urgency";
    public static final String fb_child_admin = "admin";
    public static final String fb_child_login_method = "loginMethod";

    public static final String fb_child_problems = "Problems";
    public static final String fb_child_fixed = "fixed";
    public static final String fb_child_platform = "platform";
    public static final String fb_child_problemdesc = "problemDesc";
    public static final String fb_child_problemphotourl= "problemPhotoUrl";
    public static final String fb_child_whoopened= "whoOpened";

    public static final String fb_child_value_android = "android";
    public static final String fb_child_value_ios = "ios";
    public static final String fb_child_value_bug = "bug";

    public static final String fb_child_status_sendedrequest = "sendedRequest";
    public static final String fb_child_status_friend = "friend";
    public static final String fb_child_status_waiting = "waiting";
    public static final String fb_child_status_all = "all";

    public static final String fb_child_adminid = "adminId";
    public static final String fb_child_groupphotourl = "groupPhotoUrl";
    public static final String fb_child_createdat = "createdAt";

    public static final String fb_child_usertask = "UserTask";
    public static final String fb_child_grouptask = "GroupTask";
    public static final String fb_child_assignedtime = "assignedTime";
    public static final String fb_child_completedtime = "completedTime";
    public static final String fb_child_completed = "completed";
    public static final String fb_child_closed = "closed";
    public static final String fb_child_assignedfromid = "assignedFromId";
    public static final String fb_child_taskdesc = "taskDesc";
    public static final String fb_child_members = "Members";
    public static final String fb_child_type = "type";
    public static final String fb_child_assignedfrom = "AssignedFrom";

    public static final String FCM_CODE_BODY = "body";
    public static final String FCM_CODE_TITLE = "title";
    public static final String FCM_CODE_PHOTO_URL = "photoUrl";
    public static final String FCM_CODE_NOTIFICATION = "notification";
    public static final String FCM_CODE_DATA = "data";
    public static final String FCM_CODE_TO = "to";
    public static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
    public static final String FCM_CODE_MESSAGE_ID = "MESSAGE_ID";
    public static final String FCM_CODE_USERID = "userid";

    public static final String FB_CHILD_DEVICE_TOKEN = "DeviceToken";
    public static final String FB_CHILD_TOKEN = "Token";
    public static final String FB_CHILD_SIGNIN = "SignIn";

    //Animation Tags
    public static final String ANIMATE_LEFT_TO_RIGHT = "ANIMATE_LEFT_TO_RIGHT";
    public static final String ANIMATE_RIGHT_TO_LEFT = "ANIMATE_RIGHT_TO_LEFT";
    public static final String ANIMATE_DOWN_TO_UP = "ANIMATE_DOWN_TO_UP";
    public static final String ANIMATE_UP_TO_DOWN = "ANIMATE_UP_TO_DOWN";

    public static final String photo_upload_new = "newPhoto";
    public static final String photo_upload_change = "changePhoto";

    public static final String APP_INVITATION_LINK = "https://myduties.page.link/wMv3";

    public static final String ALGOLIA_APP_ID = "0NXABIBM4D";
    public static final String ALGOLIA_SEARCH_API_KEY = "c93cf7a717073098d2d25938b3ee8d27";
    public static final String ALGOLIA_INDEX_NAME = "MyDuties";

    public static final String URGENT = "urgent";
    public static final String NOT_URGENT = "noturgent";
    public static final String ALL_URGENT = "allurgent";


    //************* numeric constants**********************

    public static final int GROUP_NAME_MAX_LENGTH = 25;
    public static final int BIO_MAX_LENGTH = 250;

    public static final int RESPONSE_OK = 1;

    //Share video properties
    public static final int MAX_VIDEO_DURATION = 15;
    public static final int MAX_VIDEO_SIZE_IN_MB =  25;

    public static final int MAX_IMAGE_SIZE_1MB =  1048576;
    public static final int MAX_IMAGE_SIZE_1ANDHALFMB =  1572864;
    public static final int MAX_IMAGE_SIZE_2ANDHALFMB =  2621440;
    public static final int MAX_IMAGE_SIZE_5MB = 5242880;

    //Photo Chosen items
    public static final int CODE_GALLERY_POSITION = 0;
    public static final int CODE_CAMERA_POSITION = 1;
    public static final int CODE_SCREENSHOT_POSITION = 1;
    public static final int CODE_PHOTO_REMOVE = 2;
    public static final int CODE_VIDEO_REMOVE = 2;
    public static final int CODE_PHOTO_EDIT = 3;
    public static final int CODE_PLAY_VIDEO = 3;

    //Phone num verify duration
    public static final int VERIFY_PHONE_NUM_DURATION = 60;

    //Feed paignation initial values
    public static final int DEFAULT_FEED_PAGE_COUNT = 1;
    public static final int DEFAULT_FEED_PERPAGE_COUNT = 15; // EN AZ 4 OLMALI.
    public static final int DEFAULT_FEED_RADIUS = 5000; //metre cinsinden
    public static int FILTERED_FEED_RADIUS = 5000; //metre cinsinden

    //Get Follower initial values
    public static final int DEFAULT_GET_FOLLOWER_PAGE_COUNT = 1;
    public static final int DEFAULT_GET_FOLLOWER_PERPAGE_COUNT = 50; // EN AZ 4 OLMALI.

    public static final int SHARE_TRY_COUNT = 2;

    //Share
    public static final int REQUEST_CODE_ENABLE_LOCATION = 3003;

    //User posts gridView pagination
    public static final int DEFAULT_PROFILE_GRIDVIEW_PAGE_COUNT = 1;
    public static final int DEFAULT_PROFILE_GRIDVIEW_PERPAGE_COUNT = 30; // EN AZ 4 OLMALI.

    //USER POST VIEW TYPE
    public static final int USER_POST_VIEW_TYPE_GRID = 0;
    public static final int USER_POST_VIEW_TYPE_LIST = 1;

    public static final int MESSAGE_LIMIT_COUNT = 25;
    public static final int REC_MAXITEM_LIMIT_COUNT = 50;

    public final static int KEYBOARD_CHECK_VALUE = 200;

    public final static int FCM_MAX_MESSAGE_LEN = 30;
    public final static int MAX_ALLOWED_NOTIFICATION_SIZE = 4;

    //Activity request codes
    public final static int REQUEST_CODE_START_MESSAGE_LIST_ACTIVITY = 1001;

    //FEED Exceptions
    public static final int VIEW_RETRY = 0;
    public static final int VIEW_NO_POST_FOUND = 1;
    public static final int VIEW_SERVER_ERROR = 4;

    //Orientation constants
    public static final int ORIENTATION_LEFT_RIGHT = 0;
    public static final int ORIENTATION_RIGHT_LEFT = 1;
    public static final int ORIENTATION_TOPLEFT_BOTTOMRIGHT = 2;
    public static final int ORIENTATION_BOTTOMRIGHT_TOPLEFT = 3;
    public static final int ORIENTATION_TOP_BOTTOM = 4;
    public static final int ORIENTATION_BOTTOM_TOP = 5;
}
