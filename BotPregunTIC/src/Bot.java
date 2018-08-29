
import com.vdurmont.emoji.EmojiParser;
import java.awt.Font;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javassist.tools.Callback;
import model.Pregunta;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 *
 * @author Daniel Garro
 */
public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getFrom().getFirstName() + ": " + update.getMessage().getText());

        //imprime chatId del cliente
        System.out.println("====>" + update.getMessage().getChatId());

        Long id = update.getMessage().getChatId();
        String mensajeRecibido = update.getMessage().getText();
        String command = update.getMessage().getText();
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId());
        SendMessage mensaje = new SendMessage().setChatId(id);
        if (command.equals("/start")) {
            System.out.println(update.getMessage().getFrom().getFirstName());
            message.setText("Hola" + " " + update.getMessage().getFrom().getFirstName() + ", estos son los comandos de PregunTICbot \n"
                    + "/preguntar ------ Enviar pregunta para el foro de PregunTIC.com. (Envía tu pregunta con el siguiente formato : /preguntar su pregunta )\n"
                    + "/informacion ------ Informacion Universidad. \n"
                    + "/serviciosEstudiantiles -------- Lista de servicios estudiantiles.");

        }
        if (command.equals("/informacion")) {
            System.out.println(update.getMessage().getFrom().getFirstName());
            message.setText("https://ulatina.ac.cr/ acá podes ver cualquier tipo de información sobre la Universida Latina");

        }
        if (mensajeRecibido.contains("/preguntar") == true) {
            /* String patron = ",(120,200)";
            /*if (update.hasMessage()&&update.getMessage().getText().matches(patron)==true){*/

            //mensajeRecibido.compareTo("/preguntar")==0
            message.setText("Su pregunta es: " + update.getMessage().getText().replace("/preguntar", "") + ("\n-Para confirmar el envio de su pregunta, envia /enviar"));
            ServicioPregunta servicio = new ServicioPregunta();
            Pregunta p = new Pregunta();
            servicio.conectar();
            p.setFecha(Date.valueOf(LocalDate.now()));
            p.setIdUsuarioCarnet(5);
            p.setIdCategoria(1);
            p.setContenido(update.getMessage().getText().replace("/preguntar",""));
            p.setTags("Pregunta Telegram");
            p.setChatId(update.getMessage().getChatId().toString());
            servicio.agregarPregunta(p);
            System.out.println(p.toString());
 
            /**
             * }else{
             * message.setText(update.getMessage().getChat().getFirstName() + ",
             * su pregunta es muy corta..."); }
             */
        }
        if (command.equals("/enviar")) {
            message.setText("Tu pregunta ha sido enviada al Foro de Preguntic.com"  + Emoji.PENCIL);
        }

        if (command.equals("/serviciosEstudiantiles")) {
            System.out.println(update.getMessage().getFrom().getFirstName());
            message.setText("Estos son los servicios para estudiantes que ofrece la Universidad Latina, escoge cual deseas adquirir más infomarción!");

            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            // Create the keyboard (list of keyboard rows)
            List<KeyboardRow> keyboard = new ArrayList<>();
            // Create a keyboard row
            KeyboardRow row = new KeyboardRow();
            // botones
            row.add("Fechas de Graduación" + EmojiParser.parseToUnicode(":mortar_board:"));
            row.add("Requisitos y Periodo de Inscripción" + EmojiParser.parseToUnicode(":bookmark_tabs:"));

            keyboard.add(row);
            // crear otro keyboar row
            row = new KeyboardRow();
            // Set each button for the second line
            row.add("Software Microsoft" + EmojiParser.parseToUnicode(":computer:"));
            row.add("Campus Virtual" + EmojiParser.parseToUnicode(":books:"));

            // agrega el segundo row
            keyboard.add(row);
            row = new KeyboardRow();
            row.add("Certificaciones y constancias" + EmojiParser.parseToUnicode(":clipboard:"));
            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);

            message.setReplyMarkup(keyboardMarkup);

        }
        if (command.equals("Fechas de Graduación" + EmojiParser.parseToUnicode(":mortar_board:"))) {

            System.out.println(update.getMessage().getFrom().getFirstName());
            message.setText("https://ulatina.ac.cr/wp-content/uploads/2018/07/calendario_graduaciones_agosto_2018.pdf");

        }
        if (!command.equals("Requisitos y Periodo de Inscripción" + EmojiParser.parseToUnicode(":bookmark_tabs:"))) {
        } else {
            System.out.println(update.getMessage().getFrom().getFirstName());
            message.setText("https://ulatina.ac.cr/wp-content/uploads/2018/08/requisito_graduacion_III_2018_correo.jpg");
        }
        
        if (!command.equals("Certificaciones y constancias" + EmojiParser.parseToUnicode(":clipboard:"))) {
        } else {

            System.out.println(update.getMessage().getFrom().getFirstName());
            message.setText("Certificaciones y constancias\n"
                    + "La Universidad emite certificaciones y constancias relativas a\n"
                    + "Certificaciones:\n"
                    + "-Certificación de conclusión de estudios\n"
                    + "-Certificación de estudiante activo\n"
                    + "-Certificación de materias aprobadas y convalidadas\n"
                    + "-Certificación de historial académico (materias aprobadas, reprobadas, retiradas, convalidadas)\n"
                    + "-Certificación de materias aprobadas y convalidadas para traslado de universidad (con programas de cursos aprobados)\n"
                    + "-Certificación de T.C.U. (Trabajo Comunal Universitario)\n"
                    + "-Certificación de tesis/proyecto/practica o pruebas de grado\n"
                    + "-Certificación de categoría \n"
                    + "-Certificación de graduado (indica periodo, tomo, folio, asiento del título)\n"
                    + "-Certificación de cuadro de detalle de Convalidación\n"
                    + "-Certificación de ranking class (graduado)\n"
                    + "-Certificación de egreso (conclusión sin tramitar graduación)\n"
                    + "-Certificación de carga horaria\n"
                    + "\n"
                    + "Constancias:\n"
                    + "-Constancia de estudiante activo\n"
                    + "-Constancia de materias aprobadas y convalidadas\n"
                    + "-Constancia de materias matriculadas\n"
                    + "-Constancia de cuadro de detalle de Convalidación\n"
                    + "-Constancia de historial académico (materias aprobadas, reprobadas, retiradas, convalidadas)\n"
                    + "-Constancia de historial académico para FONABE\n"
                    + "-Constancia de historial académico para CONAPE \n"
                    + "-Constancia de materias pendientes CONAPE\n"
                    + "-Constancia de materias pendientes\n"
                    + "\n"
                    + "Las certificaciones se entregan de 5 a 8 días hábiles, las constancias de 3 a 5 días hábiles y las certificaciones de traslado de universidad 15 días después de haberse solicitado y cancelado el arancel correspondiente.\n"
                    + "\n"
                    + "Las mismas se extienden únicamente por solicitud personal, a través de apoderados o por orden judicial.\n"
                    + "\n"
                    + "-La Universidad");
        }
        if (!command.equals("Software Microsoft" + EmojiParser.parseToUnicode(":computer:"))) {
        } else {

            System.out.println(update.getMessage().getFrom().getFirstName());
            message.setText("Software Microsoft\n"
                    + "Requerimientos de acceso:\n"
                    + "\n"
                    + " Ingresá tu usuario y contraseña al campus virtual de la Universidad Latina de Costa Rica.\n"
                    + "\n"
                    + "Windows 7 o superior.\n"
                    + "Mac OS en procesador Intel.\n"
                    + "Conexión a internet de banda ancha.\n"
                    + " Instalación:\n"
                    + "\n"
                    + "Ingresá tu usuario y contraseña al campus virtual de la Universidad Latina de Costa Rica.\n"
                    + "Elegí la opción (Ir al correo).\n"
                    + "Ingresá a la pestaña instalar office, seleccioná el botón (Instalar Ahora), seguí las instrucciones y, al finalizar la instalación, iniciá sesión en tu correo de la universidad para activar.\n"
                    + "En el correo de la universidad ingresá a la opción (buzón) dá clic en el icono engranaje y seleccioná la opción (configuración de office 365), luego (Software).\n"
                    + "Seleccioná el idioma a instalar, y dá clic en el botón (Instalar) y seguí las instrucciones. Al finalizar se te pedirá iniciar sesión con tu cuenta de correo para activar.\n"
                    + "https://e5.onthehub.com/WebStore/Welcome.aspx?ws=18a22b99-6a9b-e011-969d-0030487d8897");
        }
        if (command.equals("Campus Virtual" + EmojiParser.parseToUnicode(":books:"))) {
            message.setText("Dale click a este enlance para dirigirte al Campus Virtual. \n ------> http://campus.ulatina.ac.cr/Login/Paginas/login.aspx");

        }

        if (command.equals("/esconder")) {
            message.setChatId(id).setText("Teclado oculto...");
            ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
            message.setReplyMarkup(keyboardMarkup);

        }
        if (mensajeRecibido.contains("/respuesta")) {
            String respuestaS = command.replace("/respuesta ", "");
            respuestaS.replaceAll("\\s", "");
            Integer conv = Integer.parseInt(respuestaS);
            ServicioRespuesta sR = new ServicioRespuesta();
            String respuesta = sR.obtenerListaRespuestas(conv).toString();
            respuesta = respuesta.replace("idPregunta", " \n - Su pregunta con número de referencia: ");
            respuesta = respuesta.replace("idUsuarioCarnetRespuesta", " \n - Respondida por el Usuario con carnet: ");
            respuesta = respuesta.replace("contenido", "\n - Tiene la siguiente respuesta: ");
            respuesta = respuesta.replace("fecha", " \n - Respondida en la fecha:  ");
            message.setText(respuesta);
        }
      
                
        try {
            sendMessage(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return "@PregunTICbot";
    }

    @Override
    public String getBotToken() {
        return "605797436:AAH2Z1R_DVxiPGYAMkeVk_j9aPlh8Wio51M";
    }

}
