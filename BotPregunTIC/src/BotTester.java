import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
/**
 *
 * @author Daniel Garro
 */
public class BotTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        Bot bot = new Bot();
        
        try 
        {
            telegramBotsApi.registerBot(bot);
        }
        catch (TelegramApiRequestException e)
        {
            e.printStackTrace();
            
    }
    }
}
    
