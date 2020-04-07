package protocol.serialize;

/**
 * 序列化方法 Code 服务端可以根据code进行解码
 */
public interface   SerializerAlgorithm {
    /**
     * json 序列化
     */
    byte JSON = 1;
}
