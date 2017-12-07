package com.kasahara;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;

public class SelectorSocketServer1 {

    private Selector selector;
    private static int PORT = 9000;

    private ByteBuffer buffer = ByteBuffer.allocateDirect(2048);

    private static Charset charset = Charset.forName("UTF-16");
    private static CharsetDecoder decoder = charset.newDecoder();
        
    public SelectorSocketServer1() {
        try {
            selector = SelectorProvider.provider().openSelector();
//            selector = Selector.open();                            // �ǂ���ł�OK

            ServerSocketChannel serverSocketChannel 
                = SelectorProvider.provider().openServerSocketChannel();
//            serverSocketChannel = ServerSocketChannel.open();

            // Non-Blocking ���[�h�ɂ���
            serverSocketChannel.configureBlocking(false);

            InetSocketAddress address
                = new InetSocketAddress(InetAddress.getLocalHost(), PORT);
            serverSocketChannel.socket().bind(address);

            // Selector �ւ̓o�^
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException ex) {
            System.exit(1);
        }
    }

    public void start() {
        try {
            while (selector.select() > 0) {
                // �Z���N�g���ꂽ SelectionKey �I�u�W�F�N�g���܂Ƃ߂Ď擾����
                Iterator keyIterator = selector.selectedKeys().iterator();
                
                while (keyIterator.hasNext()) {
                    SelectionKey key = (SelectionKey)keyIterator.next();
                    keyIterator.remove();
                    
                    // �Z���N�g���ꂽ SelectionKey �̏�Ԃɉ����ď��������߂�
                    if (key.isAcceptable()) {
                        // accept �̏ꍇ

                        ServerSocketChannel serverSocketChannel 
                            = (ServerSocketChannel)key.channel();
                        accept(serverSocketChannel);

                    } else if (key.isReadable()) {
                        // �f�[�^�������Ă����Ƃ�

                        SocketChannel socketChannel = (SocketChannel)key.channel();
                        sendBack(socketChannel);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getClass());
            ex.printStackTrace();
            return;
        }
    }

    private void accept(ServerSocketChannel serverSocketChannel) throws IOException {
        SocketChannel socketChannel = serverSocketChannel.accept();

        // Non-Blocking ���[�h�ɕύX
        socketChannel.configureBlocking(false);

        // Selector �ւ̓o�^
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println(socketChannel.socket().getInetAddress() + " connect.");
    }

    private void sendBack(SocketChannel socketChannel) throws IOException {

        buffer.clear();
        
        // �f�[�^�̓ǂݍ���
        if (socketChannel.read(buffer) < 0){
            socketChannel.close();
            return;
        }
        
        // �ǂݍ��񂾃f�[�^�����̂܂ܑ���Ԃ�
        buffer.flip();
        System.out.println(socketChannel.socket().getInetAddress()
                           + " : " + decoder.decode(buffer.duplicate()));
        
        socketChannel.write(buffer);
    }

    public static void main(String[] args){
        new SelectorSocketServer1().start();
    }
}
