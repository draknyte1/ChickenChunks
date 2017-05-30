package alkalus;

import static alkalus.ChunkViewer.channel;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import alkalus.PlayerChunkViewerManager.ChunkChange;
import alkalus.PlayerChunkViewerManager.TicketChange;
import codechicken.core.CommonUtils;
import codechicken.lib.packet.PacketCustom;
import codechicken.lib.vec.Vector3;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;

public class PlayerChunkViewerTracker {
	private final PlayerChunkViewerManager manager;
	public final EntityPlayer owner;
	private HashSet<Integer> knownTickets = new HashSet<Integer>();

	public PlayerChunkViewerTracker(EntityPlayer player, PlayerChunkViewerManager manager) {
		owner = player;
		this.manager = manager;

		try {
			System.out.println("Creating a PacketCustom in "+this.getClass().getName()+"."+"PlayerChunkViewerTracker");
			PacketCustom packet = new PacketCustom(ChunkViewer.channel, 1);
			packet.sendToPlayer(player);
		}
		catch (NullPointerException n){

		}

		for (WorldServer world : DimensionManager.getWorlds())
			loadDimension(world);
	}

	public void writeTicketToPacket(PacketCustom packet, Ticket ticket, Collection<ChunkCoordIntPair> chunkSet) {

		System.out.println("Editing a PacketCustom in "+this.getClass().getName()+"."+"writeTicketToPacket");
		packet.writeInt(manager.ticketIDs.get(ticket));
		packet.writeString(ticket.getModId());
		String player = ticket.getPlayerName();
		packet.writeBoolean(player != null);
		if (player != null)
			packet.writeString(player);
		packet.writeByte(ticket.getType().ordinal());
		Entity entity = ticket.getEntity();
		if (entity != null)
			packet.writeInt(entity.getEntityId());
		packet.writeShort(chunkSet.size());
		for (ChunkCoordIntPair chunk : chunkSet) {
			packet.writeInt(chunk.chunkXPos);
			packet.writeInt(chunk.chunkZPos);
		}

		knownTickets.add(manager.ticketIDs.get(ticket));
	}

	@SuppressWarnings("unchecked")
	public void loadDimension(WorldServer world) {
		try {
			System.out.println("Creating a PacketCustom in "+this.getClass().getName()+"."+"loadDimension");
			PacketCustom packet = new PacketCustom(channel, 2).compress();

			int dim = CommonUtils.getDimension(world);
			packet.writeInt(dim);

			List<Chunk> allchunks = world.theChunkProviderServer.loadedChunks;
			packet.writeInt(allchunks.size());
			for (Chunk chunk : allchunks) {
				packet.writeInt(chunk.xPosition);
				packet.writeInt(chunk.zPosition);
			}

			Map<Ticket, Collection<ChunkCoordIntPair>> tickets = ForgeChunkManager.getPersistentChunksFor(world).inverse()
					.asMap();
			packet.writeInt(tickets.size());
			for (Entry<Ticket, Collection<ChunkCoordIntPair>> entry : tickets.entrySet())
				writeTicketToPacket(packet, entry.getKey(), entry.getValue());

			packet.sendToPlayer(owner);
		}
		catch (NullPointerException n){

		}
	}

	public void unloadDimension(int dim) {
		try {
			System.out.println("Creating a PacketCustom in "+this.getClass().getName()+"."+"unloadDimension");
			PacketCustom packet = new PacketCustom(channel, 3);
			packet.writeInt(dim);

			packet.sendToPlayer(owner);
		}
		catch (NullPointerException n){

		}
	}

	public void sendChunkChange(ChunkChange change) {
		try {
			//System.out.println("Creating a PacketCustom in "+this.getClass().getName()+"."+"sendChunkChange");
			PacketCustom packet = new PacketCustom(channel, 4);
			packet.writeInt(change.dimension);
			packet.writeInt(change.chunk.chunkXPos);
			packet.writeInt(change.chunk.chunkZPos);
			packet.writeBoolean(change.add);

			packet.sendToPlayer(owner);
		} catch (NullPointerException n){

		}
	}

	public void sendTicketChange(TicketChange change) {
		int ticketID = manager.ticketIDs.get(change.ticket);
		if (!knownTickets.contains(ticketID))
			addTicket(change.dimension, change.ticket);

		try{
			System.out.println("Creating a PacketCustom in "+this.getClass().getName()+"."+"sendTicketChange");
			PacketCustom packet = new PacketCustom(channel, 5);
			packet.writeInt(change.dimension);
			packet.writeInt(ticketID);
			packet.writeInt(change.chunk.chunkXPos);
			packet.writeInt(change.chunk.chunkZPos);
			packet.writeBoolean(change.force);

			packet.sendToPlayer(owner);
		}
		catch (NullPointerException n){

		}
	}

	public void updatePlayer(EntityPlayer player) {
		try {
			System.out.println("Creating a PacketCustom in "+this.getClass().getName()+"."+"updatePlayer");
			PacketCustom packet = new PacketCustom(channel, 6);
			packet.writeString(player.getCommandSenderName());
			packet.writeInt(player.dimension);
			Vector3 pos = Vector3.fromEntity(player);
			packet.writeFloat((float) pos.x);
			packet.writeFloat((float) pos.y);
			packet.writeFloat((float) pos.z);

			packet.sendToPlayer(owner);
		}
		catch (NullPointerException n){

		}
	}

	public void removePlayer(String username) {
		try {
			System.out.println("Creating a PacketCustom in "+this.getClass().getName()+"."+"removePlayer");
			PacketCustom packet = new PacketCustom(channel, 7);
			packet.writeString(username);

			packet.sendToPlayer(owner);
		}
		catch (NullPointerException n){

		}
	}

	public void addTicket(int dimension, Ticket ticket) {
		try {
			System.out.println("Creating a PacketCustom in "+this.getClass().getName()+"."+"addTicket");
			PacketCustom packet = new PacketCustom(channel, 8);
			packet.writeInt(dimension);
			writeTicketToPacket(packet, ticket, ticket.getChunkList());

			packet.sendToPlayer(owner);
		}
		catch (NullPointerException n){

		}
	}

}
