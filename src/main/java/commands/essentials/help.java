package commands.essentials;

import commands.Command;
import core.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Logger;
import util.STATICS;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 26.09.2017 19:07
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class help implements Command {

    EmbedBuilder eb = new EmbedBuilder();

    private String getPermPre(int lvl) {
        switch (lvl) {
            case 1: return ":one:";
            case 2: return ":two:";
            case 3: return ":three:";
            default: return ":1234:";
        }
    }


    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        if (args.length > 0) {
            if (Main.commands.containsKey(args[0]))
                if (Main.commands.get(args[0]).help() != null)
                    event.getTextChannel().sendMessage(
                            eb.setColor(new Color(22, 138, 233)).setDescription(Main.commands.get(args[0]).help()).build()
                    ).queue();
                else
                    event.getTextChannel().sendMessage(
                            eb.setColor(Color.red).setDescription(":warning:  There is no information for the command  *-" + args[0] + "* !").build()
                    ).queue();
            else
                event.getTextChannel().sendMessage(
                        eb.setColor(Color.red).setDescription(":warning:  The command list does not contains information for the command *-" + args[0] + "* !").build()
                ).queue();
            return;
        }


        event.getMessage().delete().queue();

        Map<String, String> cmds = new TreeMap<>();
        Main.commands.forEach((s, command) -> cmds.put(s, command.description()));

        StringBuilder ciams = new StringBuilder();


        String[] ignorers = {};
        Arrays.stream(ignorers).forEach(s -> cmds.remove(s));

        try {

            PrivateChannel pc = event.getAuthor().openPrivateChannel().complete();
            pc.sendMessage(":clipboard:  __**COMMAND LIST**__  :clipboard: \n\n" +
                    ":point_right:   Dreißt kopiert von **https://github.com/zekroTJA**\n\n" +
                    "***Legend:***\n" +
                    "  :1234:  -  Usable for everyone\n" +
                    "  :one:  -  Only for groups `" + Arrays.toString(STATICS.PERMS).replace("[", "").replace("]", "") + "`\n" +
                    "  :two:  -  Only for groups `" + Arrays.toString(STATICS.FULLPERMS).replace("[", "").replace("]", "") + "`\n" +
                    "  :three:  -  Only for owner of the server\n"
                    + "\n\n___").queue();

            ciams.delete(0, ciams.length());
            ciams.append("**" + STATICS.CMDTYPE.administration + "**\n");
            cmds.keySet().stream()
                    .filter(s -> Main.commands.get(s).commandType().equals(STATICS.CMDTYPE.administration))
                    .forEach(s1 -> ciams.append(
                            getPermPre(Main.commands.get(s1).permission()) + "  **" + s1 + "**   -   `" + cmds.get(s1) + "`\n"
                    ));
            pc.sendMessage(new EmbedBuilder().setColor(new Color(134, 255, 0)).setDescription(ciams.toString()).build()).queue();

            ciams.delete(0, ciams.length());
            ciams.append("**" + STATICS.CMDTYPE.chatutils + "**\n");
            cmds.keySet().stream()
                    .filter(s -> Main.commands.get(s).commandType().equals(STATICS.CMDTYPE.chatutils))
                    .forEach(s1 -> ciams.append(
                            getPermPre(Main.commands.get(s1).permission()) + "  **" + s1 + "**   -   `" + cmds.get(s1) + "`\n"
                    ));
            pc.sendMessage(new EmbedBuilder().setColor(new Color(255, 97, 0)).setDescription(ciams.toString()).build()).queue();

            ciams.delete(0, ciams.length());
            ciams.append("**" + STATICS.CMDTYPE.essentials + "**\n");
            cmds.keySet().stream()
                    .filter(s -> Main.commands.get(s).commandType().equals(STATICS.CMDTYPE.essentials))
                    .forEach(s1 -> ciams.append(
                            getPermPre(Main.commands.get(s1).permission()) + "  **" + s1 + "**   -   `" + cmds.get(s1) + "`\n"
                    ));
            pc.sendMessage(new EmbedBuilder().setColor(new Color(255, 0, 213)).setDescription(ciams.toString()).build()).queue();

            ciams.delete(0, ciams.length());
            ciams.append("**" + STATICS.CMDTYPE.etc + "**\n");
            cmds.keySet().stream()
                    .filter(s -> Main.commands.get(s).commandType().equals(STATICS.CMDTYPE.etc))
                    .forEach(s1 -> ciams.append(
                            getPermPre(Main.commands.get(s1).permission()) + "  **" + s1 + "**   -   `" + cmds.get(s1) + "`\n"
                    ));
            pc.sendMessage(new EmbedBuilder().setColor(new Color(39, 0, 255)).setDescription(ciams.toString()).build()).queue();

            ciams.delete(0, ciams.length());
            ciams.append("**" + STATICS.CMDTYPE.guildadmin + "**\n");
            cmds.keySet().stream()
                    .filter(s -> Main.commands.get(s).commandType().equals(STATICS.CMDTYPE.guildadmin))
                    .forEach(s1 -> ciams.append(
                            getPermPre(Main.commands.get(s1).permission()) + "  **" + s1 + "**   -   `" + cmds.get(s1) + "`\n"
                    ));
            pc.sendMessage(new EmbedBuilder().setColor(new Color(0, 233, 255)).setDescription(ciams.toString()).build()).queue();

            ciams.delete(0, ciams.length());
            ciams.append("**" + STATICS.CMDTYPE.music + "**\n");
            cmds.keySet().stream()
                    .filter(s -> Main.commands.get(s).commandType().equals(STATICS.CMDTYPE.music))
                    .forEach(s1 -> ciams.append(
                            getPermPre(Main.commands.get(s1).permission()) + "  **" + s1 + "**   -   `" + cmds.get(s1) + "`\n"
                    ));
            pc.sendMessage(new EmbedBuilder().setColor(new Color(0, 255, 126)).setDescription(ciams.toString()).build()).queue();

            ciams.delete(0, ciams.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        Logger.logCommand("help", event);
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public String description() {
        return "HILFE!!!!!!!!!!";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.essentials;
    }

    @Override
    public int permission() {
        return 0;
    }
}