package com.sekwah.advancedportals.compat;

import com.sekwah.advancedportals.AdvancedPortalsPlugin;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author sekwah41
 */
public class CraftBukkit {

    private final AdvancedPortalsPlugin plugin;

    private Method serializeMessage;
    private Constructor<?> chatPacketConstructor;
    private Method playerGetHandle;
    private Field playerConnection;
    private Method sendPacket;

    public CraftBukkit(AdvancedPortalsPlugin plugin, String version) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException {
        this.plugin = plugin;
        
        this.setupMessagePacket(version);
    }

    private void setupMessagePacket(String version) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException {
        // Get the methods and such to invoke later when running the actual plugin
        String craftBukkitPackage = "org.bukkit.craftbukkit." + version + ".";
        String minecraftPackage = "net.minecraft.server." + version + ".";

        Class<?> chatBaseComponent = Class.forName(minecraftPackage + "IChatBaseComponent"); // string to packet methods
        this.serializeMessage = this.findClass(chatBaseComponent, "ChatSerializer").getMethod("a", String.class);
        this.chatPacketConstructor = Class.forName(minecraftPackage + "PacketPlayOutChat").getConstructor(chatBaseComponent, byte.class);

        this.playerGetHandle = Class.forName(craftBukkitPackage + "entity.CraftPlayer").getMethod("getHandle");
        this.playerConnection = Class.forName(minecraftPackage + "EntityPlayer").getField("playerConnection"); // get player connection
        Class<?> packet = Class.forName(minecraftPackage + "Packet");
        this.sendPacket = playerConnection.getType().getMethod("sendPacket", packet);
    }

    public void sendRawMessage(String rawMessage, Player player) {
        this.sendMessage(rawMessage,player, (byte) 1);
    }

    public void sendActionBarMessage(String rawMessage, Player player) {
        this.sendMessage(rawMessage, player, (byte) 2);
    }

    /**
     * What this is doing
     * /*IChatBaseComponent comp = IChatBaseComponent.ChatSerializer.a(rawMessage);
     * // "json message", position(0: chat (chat box), 1: system message (chat box), 2: above action bar)
     * PacketPlayOutChat packet = new PacketPlayOutChat(comp, (byte) 1);
     * ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
     */
    public void sendMessage(String rawMessage, Player player, byte msgType) {
        try {
            Object chatComp = this.serializeMessage.invoke(null,rawMessage); // convert string into bytes
            Object packet = this.chatPacketConstructor.newInstance(chatComp, msgType); // convert bytes into packet

            Object handle = this.playerGetHandle.invoke(player);
            Object playerConnection = this.playerConnection.get(handle); // get players connection
            sendPacket.invoke(playerConnection, packet); // send packet
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            this.plugin.getLogger().warning("Error creating raw message, something is wrong with the reflection.");
            this.plugin.getLogger().warning("Please report this message along with the following stacktrace to sewkah");
            e.printStackTrace();
        }

    }

    /**
     * Find the class inside the class (as there is no raw method in java)
     * @param classObj
     * @param className
     * @return
     */
    public Class<?> findClass(Class<?> classObj, String className){
        for(Class<?> classes : classObj.getDeclaredClasses()){
            if(classes.getSimpleName().equals(className)){
                return classes;
            }
        }
        return null;
    }
}
