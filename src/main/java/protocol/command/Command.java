package protocol.command;

/**
 *  tom 预计完成以下功能
 */
public interface Command {

    /**
     * 登录
     */
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;
    /**
     * 信息交互
     */
    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
    /**
     * 登出
     */
    Byte LOGOUT_REQUEST = 5;

    Byte LOGOUT_RESPONSE = 6;
    /**
     * 建群
     */
    Byte CREATE_GROUP_REQUEST = 7;

    Byte CREATE_GROUP_RESPONSE = 8;
    /**
     * 群消息列表
     */
    Byte LIST_GROUP_MEMBERS_REQUEST = 9;

    Byte LIST_GROUP_MEMBERS_RESPONSE = 10;
    /**
     * 加群
     */
    Byte JOIN_GROUP_REQUEST = 11;

    Byte JOIN_GROUP_RESPONSE = 12;
    /**
     * 退群
     */
    Byte QUIT_GROUP_REQUEST = 13;

    Byte QUIT_GROUP_RESPONSE = 14;
    /**
     * 群消息交互
     */
    Byte GROUP_MESSAGE_REQUEST = 15;

    Byte GROUP_MESSAGE_RESPONSE = 16;
    /**
     * 业务心跳
     */
    Byte HEARTBEAT_REQUEST = 17;

    Byte HEARTBEAT_RESPONSE = 18;
    /**
     * 业务信息
     */
    Byte BUSSINESS_CODEC = 19;
}
