/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

//import javax.swing.SwingUtilities;
//import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.SwingUtilities;
//import javax.swing.JApplet;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author User
 */

/*
 * Создаем общедоступный класс NewJFrame, который является наследником класса
 * JFrame, в пакете swing, вложенном в пакет javax
*/
public class NewJFrame extends javax.swing.JFrame {
    
    private String portName = "";
    private int baudRate = SerialPort.BAUDRATE_115200;
    private int dataBits = SerialPort.DATABITS_8;
    private int stopBits = SerialPort.STOPBITS_1;
    private int parity = SerialPort.PARITY_NONE;
    private SerialPort serialPort; // Создаем переменную serialPort типа SerialPort
    private String str;
    private String[] commandString; // Хранилище командных строк
    private int commandStringCounter; //Счетчик командных строк

    /**
     * Creates new form NewJFrame
     */
    
    /*
     * Создаем общедоступный конструктор NewJFrame()
     *
    */
    public NewJFrame() {
        initComponents(); //Рисуем форму
        enableControls(false); //Делаем все неактивным
        //commandStringCounter = 0; //
        //for (int i=1; i<5; i++) {
            
            //commandString[0]="AT"; 
            //System.out.println("commandString");
        //}
        //System.out.println("commandString");
        String[] ports = SerialPortList.getPortNames(); //Получаем имена COM-портов
        if(ports.length > 0){ //Если число COM-портов в системе больше нуля
            portName = ports[0];// то выбираем первый COM-порт из списка
        }
        updatePortInfo();
        jPanel1.setBorder(NimbusGui.INFO_PANEL_BORDER);
        //serialPort.
    }
    
    public void updatePortSettings(String portName, int baudRate, int dataBits, int stopBits, int parity) {
        this.portName = portName;
        this.baudRate = baudRate;
        this.dataBits = dataBits;
        this.stopBits = stopBits;
        this.parity = parity;
        updatePortInfo();
    }
        
    // Обновление информации о выбраном COM-порте и о его параметрах, а так же, активация или деактивация кнопки Open port 
    private boolean updatePortInfo() {
        boolean returnValue = false;
        String info = ""; //создаем строковую переменную info и заполняем ее названием и параметрами COM-порта
        if(!portName.equals("")){
            info += (portName + " @ ");
            info += (baudRate + "-");
            info += (dataBits + "-");
            switch (parity) {
                case SerialPort.PARITY_NONE:
                    info += ("N-");
                    break;
                case SerialPort.PARITY_EVEN:
                    info += ("E-");
                    break;
                case SerialPort.PARITY_ODD:
                    info += ("O-");
                    break;
                case SerialPort.PARITY_SPACE:
                    info += ("S-");
                    break;
                case SerialPort.PARITY_MARK:
                    info += ("M-");
                    break;
            }
            switch (stopBits) {
                case SerialPort.STOPBITS_1:
                    info += ("1");
                    break;
                case SerialPort.STOPBITS_1_5:
                    info += ("1.5");
                    break;
                case SerialPort.STOPBITS_2:
                    info += ("2");
                    break;
            }
            jLabelPortInfo.setText(info); // отображаем выбраный COM-порт и его параметры
            jButtonOpenPort.setEnabled(true); //активируем кнопку Open port
            returnValue = true;
        }
        else {
            jLabelPortInfo.setText("");//а если в системе не обнаружено ни одного COM-порта, выводим пустую строку
            jButtonOpenPort.setEnabled(false);// и деактивируем  кнопку Open port
        }
        return returnValue;
    }
    
    private void enableControls(boolean value) {
        jButtonSettings.setEnabled(!value);
        jComboBoxIn.setEnabled(value);
        jButtonClear.setEnabled(value);
        jLabelCTS.setEnabled(value);
        jLabelDSR.setEnabled(value);
        jLabelRLSD.setEnabled(value);
        jLabelCTS.setOpaque(value);
        jLabelDSR.setOpaque(value);
        jLabelRLSD.setOpaque(value);
        jTextAreaIn.setEnabled(value);
        jTextFieldOut.setEnabled(value);
        jButtonSend.setEnabled(value);
        jButtonSendHEX.setEnabled(value);
        jToggleButtonRTS.setEnabled(value);
        jToggleButtonDTR.setEnabled(value);
    }
    
    public void setControlsFocusable(boolean value) {
        jButtonOpenPort.setFocusable(value);       
        jButtonSettings.setFocusable(value);
        jComboBoxIn.setFocusable(value);
        jButtonClear.setFocusable(value);
        jTextAreaIn.setFocusable(value);
        jTextFieldOut.setFocusable(value);
        jButtonSend.setFocusable(value);
        jButtonSendHEX.setFocusable(value);
        jToggleButtonRTS.setFocusable(value);
        jToggleButtonDTR.setFocusable(value);
    }
    
    private void clearFields() {
        jComboBoxIn.setSelectedIndex(0);
        jTextAreaIn.setText("");
        jTextFieldOut.setText("");
        jToggleButtonRTS.setSelected(false);
        jToggleButtonDTR.setSelected(false);
    }
    
    private void sendString() {
        str = jTextFieldOut.getText();
        if(str.length() > 0){
            //System.out.println ("Счетчик командных строк="+commandStringCounter);
            //commandString[commandStringCounter] = str;
            //commandStringCounter = commandStringCounter+1;
            try {
                serialPort.writeBytes(jTextFieldOut.getText().getBytes());
                //serialPort.writeInt(13);
                jTextFieldOut.setText("");
            }
            catch (Exception ex) {
                DialogMessage dialogMessage = new DialogMessage(this, DialogMessage.TYPE_ERROR, "Writing data", "Error occurred while writing data.");
            }
        }
    }
    
    private void sendCode(int code) {        
            try {
                serialPort.writeInt(code);                
                jTextFieldOut.setText("");
            }
            catch (Exception ex) {
                DialogMessage dialogMessage = new DialogMessage(this, DialogMessage.TYPE_ERROR, "Writing data", "Error occurred while writing data.");
            }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel(){

            protected void paintComponent(Graphics g){
                GradientPaint paint = new GradientPaint(0, 0, NimbusGui.INFO_PANEL_TOP_COLOR, 0, getHeight(), NimbusGui.INFO_PANEL_BOTTOM_COLOR);
                Graphics2D graphics2D = (Graphics2D)g.create();
                graphics2D.setPaint(paint);
                graphics2D.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jAppName = new javax.swing.JLabel();
        jLabelPortInfo = new javax.swing.JLabel();
        jButtonOpenPort = new javax.swing.JButton();
        jButtonSettings = new javax.swing.JButton();
        jHeaderPanelInput = new javax.swing.JPanel() {
            protected void paintComponent(Graphics g){
                GradientPaint paint = new GradientPaint(0, 0, NimbusGui.INFO_PANEL_TOP_COLOR, 0, getHeight(), NimbusGui.INFO_PANEL_BOTTOM_COLOR);
                Graphics2D graphics2D = (Graphics2D)g.create();
                graphics2D.setPaint(paint);
                graphics2D.fillRect(0, 0, getWidth(), getHeight());
            }

        };
        jHeaderLabelInput = new javax.swing.JLabel();
        jInputDataPanel = new javax.swing.JPanel();
        jLabelView = new javax.swing.JLabel();
        jComboBoxIn = new javax.swing.JComboBox();
        jButtonClear = new javax.swing.JButton();
        jLabelCTS = new javax.swing.JLabel();
        jLabelDSR = new javax.swing.JLabel();
        jLabelRLSD = new javax.swing.JLabel();
        jScrollPaneIn = new javax.swing.JScrollPane();
        jTextAreaIn = new javax.swing.JTextArea();
        jHeaderPanelOutput = new javax.swing.JPanel(){

            protected void paintComponent(Graphics g){
                GradientPaint paint = new GradientPaint(0, 0, NimbusGui.INFO_PANEL_TOP_COLOR, 0, getHeight(), NimbusGui.INFO_PANEL_BOTTOM_COLOR);
                Graphics2D graphics2D = (Graphics2D)g.create();
                graphics2D.setPaint(paint);
                graphics2D.fillRect(0, 0, getWidth(), getHeight());
            }
        }
        ;
        jHeaderLabelOutput = new javax.swing.JLabel();
        jOutputDataPanel = new javax.swing.JPanel();
        jTextFieldOut = new javax.swing.JTextField();
        jButtonSend = new javax.swing.JButton();
        jButtonSendHEX = new javax.swing.JButton();
        jSeparatorOut = new javax.swing.JSeparator();
        jToggleButtonRTS = new javax.swing.JToggleButton();
        jToggleButtonDTR = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("jSSC-Terminal");
        setMaximumSize(new java.awt.Dimension(300, 400));

        jPanel1.setBackground(new Color(156, 183, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(747, 40));

        jAppName.setFont(NimbusGui.INFO_PANEL_FONT);
        jAppName.setForeground(NimbusGui.INFO_PANEL_FONT_COLOR);
        jAppName.setText("jSSC-Terminal");
        jAppName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jAppName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jAppNameMouseClicked(evt);
            }
        });

        jLabelPortInfo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelPortInfo.setForeground(NimbusGui.SECTION_LABEL_FONT_COLOR);
        jLabelPortInfo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelPortInfo.setText("jPanel1");

        jButtonOpenPort.setFont(NimbusGui.DEFAULT_FONT);
        jButtonOpenPort.setText("Open port");
        jButtonOpenPort.setPreferredSize(new java.awt.Dimension(100, 28));
        jButtonOpenPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenPortActionPerformed(evt);
            }
        });

        jButtonSettings.setFont(NimbusGui.DEFAULT_FONT);
        jButtonSettings.setText("Settings");
        jButtonSettings.setPreferredSize(new java.awt.Dimension(75, 28));
        jButtonSettings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButtonSettingsMouseExited(evt);
            }
        });
        jButtonSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSettingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jAppName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelPortInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonOpenPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOpenPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPortInfo)
                    .addComponent(jAppName))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jHeaderPanelInput.setBorder(NimbusGui.WORK_AND_DATA_PANEL_BORDER);
        jHeaderPanelInput.setAlignmentX(0.0F);
        jHeaderPanelInput.setAlignmentY(0.0F);
        jHeaderPanelInput.setPreferredSize(new java.awt.Dimension(286, 30));

        jHeaderLabelInput.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jHeaderLabelInput.setForeground(NimbusGui.SECTION_LABEL_FONT_COLOR);
        jHeaderLabelInput.setText("Input");

        javax.swing.GroupLayout jHeaderPanelInputLayout = new javax.swing.GroupLayout(jHeaderPanelInput);
        jHeaderPanelInput.setLayout(jHeaderPanelInputLayout);
        jHeaderPanelInputLayout.setHorizontalGroup(
            jHeaderPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jHeaderPanelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jHeaderLabelInput)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jHeaderPanelInputLayout.setVerticalGroup(
            jHeaderPanelInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jHeaderPanelInputLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jHeaderLabelInput)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jInputDataPanel.setBackground(new java.awt.Color(231, 233, 237));
        jInputDataPanel.setBorder(NimbusGui.WORK_AND_DATA_PANEL_BORDER);
        jInputDataPanel.setAlignmentX(0.0F);
        jInputDataPanel.setAlignmentY(0.0F);
        jInputDataPanel.setPreferredSize(new java.awt.Dimension(276, 75));

        jLabelView.setFont(NimbusGui.DEFAULT_FONT);
        jLabelView.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelView.setText("View as:");
        jLabelView.setPreferredSize(new java.awt.Dimension(40, 28));

        jComboBoxIn.setFont(NimbusGui.DEFAULT_FONT);
        jComboBoxIn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chars", "Digits", "HEX" }));
        jComboBoxIn.setPreferredSize(new java.awt.Dimension(100, 28));

        jButtonClear.setFont(NimbusGui.DEFAULT_FONT);
        jButtonClear.setText("Clear");
        jButtonClear.setPreferredSize(new java.awt.Dimension(90, 28));
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jLabelCTS.setFont(NimbusGui.DEFAULT_FONT);
        jLabelCTS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCTS.setText("CTS");
        jLabelCTS.setPreferredSize(new java.awt.Dimension(34, 28));

        jLabelDSR.setFont(NimbusGui.DEFAULT_FONT);
        jLabelDSR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDSR.setText("DSR");
        jLabelDSR.setPreferredSize(new java.awt.Dimension(34, 28));

        jLabelRLSD.setFont(NimbusGui.DEFAULT_FONT);
        jLabelRLSD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRLSD.setText("RLSD");
        jLabelRLSD.setPreferredSize(new java.awt.Dimension(34, 28));

        jTextAreaIn.setEditable(false);
        jTextAreaIn.setColumns(20);
        jTextAreaIn.setFont(NimbusGui.DEFAULT_FONT);
        jTextAreaIn.setLineWrap(true);
        jTextAreaIn.setRows(5);
        jTextAreaIn.setWrapStyleWord(true);
        jScrollPaneIn.setViewportView(jTextAreaIn);

        javax.swing.GroupLayout jInputDataPanelLayout = new javax.swing.GroupLayout(jInputDataPanel);
        jInputDataPanel.setLayout(jInputDataPanelLayout);
        jInputDataPanelLayout.setHorizontalGroup(
            jInputDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInputDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInputDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneIn)
                    .addGroup(jInputDataPanelLayout.createSequentialGroup()
                        .addComponent(jLabelView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelCTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelDSR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelRLSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jInputDataPanelLayout.setVerticalGroup(
            jInputDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInputDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInputDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRLSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDSR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPaneIn, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );

        jHeaderPanelOutput.setBorder(NimbusGui.WORK_AND_DATA_PANEL_BORDER);
        jHeaderPanelOutput.setPreferredSize(new java.awt.Dimension(286, 30));

        jHeaderLabelOutput.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jHeaderLabelOutput.setForeground(NimbusGui.SECTION_LABEL_FONT_COLOR);
        jHeaderLabelOutput.setText("Output");

        javax.swing.GroupLayout jHeaderPanelOutputLayout = new javax.swing.GroupLayout(jHeaderPanelOutput);
        jHeaderPanelOutput.setLayout(jHeaderPanelOutputLayout);
        jHeaderPanelOutputLayout.setHorizontalGroup(
            jHeaderPanelOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jHeaderPanelOutputLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jHeaderLabelOutput)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jHeaderPanelOutputLayout.setVerticalGroup(
            jHeaderPanelOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jHeaderPanelOutputLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jHeaderLabelOutput)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jOutputDataPanel.setBackground(new java.awt.Color(231, 233, 237));
        jOutputDataPanel.setBorder(NimbusGui.WORK_AND_DATA_PANEL_BORDER);
        jOutputDataPanel.setPreferredSize(new java.awt.Dimension(276, 75));

        jTextFieldOut.setFont(NimbusGui.DEFAULT_FONT);
        jTextFieldOut.setPreferredSize(new java.awt.Dimension(59, 28));
        jTextFieldOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldOutActionPerformed(evt);
            }
        });
        jTextFieldOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldOutKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldOutKeyTyped(evt);
            }
        });

        jButtonSend.setFont(NimbusGui.DEFAULT_FONT);
        jButtonSend.setText("Send");
        jButtonSend.setPreferredSize(new java.awt.Dimension(90, 28));
        jButtonSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendActionPerformed(evt);
            }
        });

        jButtonSendHEX.setFont(NimbusGui.DEFAULT_FONT);
        jButtonSendHEX.setText("Send HEX");
        jButtonSendHEX.setPreferredSize(new java.awt.Dimension(90, 28));
        jButtonSendHEX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendHEXActionPerformed(evt);
            }
        });

        jSeparatorOut.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparatorOut.setPreferredSize(new java.awt.Dimension(2, 28));

        jToggleButtonRTS.setFont(NimbusGui.DEFAULT_FONT);
        jToggleButtonRTS.setText("Set RTS");
        jToggleButtonRTS.setPreferredSize(new java.awt.Dimension(75, 28));
        jToggleButtonRTS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonRTSActionPerformed(evt);
            }
        });

        jToggleButtonDTR.setFont(NimbusGui.DEFAULT_FONT);
        jToggleButtonDTR.setText("Set DTR");
        jToggleButtonDTR.setPreferredSize(new java.awt.Dimension(75, 28));
        jToggleButtonDTR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonDTRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jOutputDataPanelLayout = new javax.swing.GroupLayout(jOutputDataPanel);
        jOutputDataPanel.setLayout(jOutputDataPanelLayout);
        jOutputDataPanelLayout.setHorizontalGroup(
            jOutputDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jOutputDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSendHEX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparatorOut, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButtonRTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButtonDTR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jOutputDataPanelLayout.setVerticalGroup(
            jOutputDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jOutputDataPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jOutputDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jOutputDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jToggleButtonDTR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToggleButtonRTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jOutputDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldOut, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSendHEX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparatorOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jHeaderPanelInput, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(jInputDataPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(jOutputDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                    .addComponent(jHeaderPanelOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jHeaderPanelInput, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jInputDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jHeaderPanelOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jOutputDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOpenPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOpenPortActionPerformed
        // TODO add your handling code here:
        //DialogMessage dialogMessage = new DialogMessage(this, DialogMessage.TYPE_ERROR, "Port opening", "Can't open port. Maybe port in use.");
        if(jButtonOpenPort.getText().equals("Open port")){ //если на кнопке написано "Open port"
            serialPort = new SerialPort(portName); // в переменную serialPort заносим выбраный COM-порт
            
            try {
                if(serialPort.openPort()){ // Пытаемся открыть порт, если он открывается, то
                    if(serialPort.setParams(baudRate, dataBits, stopBits, parity)){ // пытаемся установить параметры порта, если они устанавливаются, то 
                        jButtonOpenPort.setText("Close port"); // меняем надпись на кнопке на "Close port"
                        /*
                        Добавляем прослушиватель событий.
                        В качестве параметров в методе задаются:
                            1) объект типа "SerialPortEventListener" 
                        Этот объект должен быть должным образом описан, как он 
                        будет отвечать за обработку произошедших событий.
                            2) маска событий. чтобы сделать ее нужно 
                        использовать переменные с префиксом "MASK_" например 
                        "MASK_RXCHAR"
                        */
                        serialPort.addEventListener(new Reader(), SerialPort.MASK_RXCHAR |
                                                                  SerialPort.MASK_RXFLAG |
                                                                  SerialPort.MASK_CTS |
                                                                  SerialPort.MASK_DSR |
                                                                  SerialPort.MASK_RLSD);
                        enableControls(true);
                        if(serialPort.isCTS()){
                            jLabelCTS.setBorder(NimbusGui.borderStatusOn);
                            jLabelCTS.setBackground(NimbusGui.colorStatusOnBG);
                        }
                        else {
                            jLabelCTS.setBorder(NimbusGui.borderStatusOff);
                            jLabelCTS.setBackground(NimbusGui.colorStatusOffBG);
                        }
                        if(serialPort.isDSR()){
                            jLabelDSR.setBorder(NimbusGui.borderStatusOn);
                            jLabelDSR.setBackground(NimbusGui.colorStatusOnBG);
                        }
                        else {
                            jLabelDSR.setBorder(NimbusGui.borderStatusOff);
                            jLabelDSR.setBackground(NimbusGui.colorStatusOffBG);
                        }
                        if(serialPort.isRLSD()){
                            jLabelRLSD.setBorder(NimbusGui.borderStatusOn);
                            jLabelRLSD.setBackground(NimbusGui.colorStatusOnBG);
                        }
                        else {
                            jLabelRLSD.setBorder(NimbusGui.borderStatusOff);
                            jLabelRLSD.setBackground(NimbusGui.colorStatusOffBG);
                        }
                        if(serialPort.setRTS(true)){
                            jToggleButtonRTS.setSelected(true);
                        }
                        if(serialPort.setDTR(true)){
                            jToggleButtonDTR.setSelected(true);
                        }
                    }
                    else {
                        //DialogMessage dialogMessage = new DialogMessage(this, DialogMessage.TYPE_ERROR, "Setting parameters", "Can't set selected parameters.");
                        serialPort.closePort();
                    }
                }
                else {
                    //DialogMessage dialogMessage = new DialogMessage(this, DialogMessage.TYPE_ERROR, "Port opening", "Can't open port. Maybe port in use.");
                }
            }
            catch (SerialPortException ex) {
                DialogMessage dialogMessage = new DialogMessage(this, DialogMessage.TYPE_ERROR, ex.getPortName(), ex.getExceptionType());
                //Do nothing
            }
        }
        else {
            try {
                if(serialPort.closePort()){
                    jButtonOpenPort.setText("Open port");
                    jLabelCTS.setBorder(null);
                    jLabelDSR.setBorder(null);
                    jLabelRLSD.setBorder(null);
                    enableControls(false);
                    //clearFields();
                }
                else {
                    //DialogMessage dialogMessage = new DialogMessage(this, DialogMessage.TYPE_ERROR, "Port closing", "Can't close port.");
                }
            }
            catch (SerialPortException ex) {
                DialogMessage dialogMessage = new DialogMessage(this, DialogMessage.TYPE_ERROR, "Port closing", "Can't close port.");
                //Do nothing
            }
        }
    }//GEN-LAST:event_jButtonOpenPortActionPerformed

    private void jButtonSendHEXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendHEXActionPerformed
        // TODO add your handling code here:
        setControlsFocusable(false);
        GlassPane2 glassPane = new GlassPane2(); //создаем объект с именем glassPane
        DialogHEX dialog = new DialogHEX(this, serialPort);
        glassPane.add(dialog); //добавляем к нему dialog
        int x = (getWidth()/2) - dialog.getWidth()/2;
        int y = (getHeight()/2) - dialog.getHeight()/2;
        dialog.setBounds(x, y, dialog.getWidth(), dialog.getHeight());//размещаем dialog по центру родительского окна
        this.setGlassPane(glassPane);//Устанавливаем свойства glassPane
        glassPane.setVisible(true);//делаем glassPane видимой
    }//GEN-LAST:event_jButtonSendHEXActionPerformed

    private void jAppNameMouseClicked(java.awt.event.MouseEvent evt) {       
//GEN-FIRST:event_jAppNameMouseClicked
        // TODO add your handling code here:
        /*
         * создаем объект с именем dialogAbout с помощью конструктора класса 
         * DialogAbout2. В качестве параметра должен быть указан родитель, с 
         * типом NewJFrame. В данном случае в качестве параметра передаем этот
         * NewJFrame        
        */
        DialogAbout2 dialogAbout = new DialogAbout2(this);
        
    }//GEN-LAST:event_jAppNameMouseClicked

    private void jButtonSettingsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSettingsMouseExited
        // TODO add your handling code here:
        validate();
        repaint();
    }//GEN-LAST:event_jButtonSettingsMouseExited

    private void jButtonSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSettingsActionPerformed
        // TODO add your handling code here:
        DialogSettings dialogSettings = new DialogSettings(this, portName, baudRate, dataBits, stopBits, parity);
    }//GEN-LAST:event_jButtonSettingsActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        // TODO add your handling code here:
        jTextAreaIn.setText("");
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jTextFieldOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldOutActionPerformed
        // TODO add your handling code here:
        sendString();
    }//GEN-LAST:event_jTextFieldOutActionPerformed

    private void jButtonSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendActionPerformed
        // TODO add your handling code here:
        sendString();
    }//GEN-LAST:event_jButtonSendActionPerformed

    private void jToggleButtonRTSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonRTSActionPerformed
        // TODO add your handling code here:
        try {
            serialPort.setRTS(jToggleButtonRTS.isSelected());
        }
        catch (Exception ex) {
            DialogMessage dialogMessage = new DialogMessage(this, DialogMessage.TYPE_ERROR, "Changing RTS state", "Error occurred while changing RTS state.");
        }
    }//GEN-LAST:event_jToggleButtonRTSActionPerformed

    private void jToggleButtonDTRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonDTRActionPerformed
        // TODO add your handling code here:
        try {
            serialPort.setDTR(jToggleButtonDTR.isSelected());
        }
        catch (Exception ex) {
            DialogMessage dialogMessage = new DialogMessage(this, DialogMessage.TYPE_ERROR, "Changing DTR state", "Error occurred while changing DTR state.");
        }
    }//GEN-LAST:event_jToggleButtonDTRActionPerformed

    private void jTextFieldOutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldOutKeyTyped
        // TODO add your handling code here:       
    }//GEN-LAST:event_jTextFieldOutKeyTyped

    private void jTextFieldOutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldOutKeyPressed
        // TODO add your handling code here:
        //System.out.println (evt.getKeyCode());
        if (evt.isControlDown()) {
            if (evt.getKeyCode()== 90) {
             //System.out.println ("Ctrl-Z нажато");
             sendCode(26);
            }                        
        }
        /*
        if (evt.getKeyCode()== 38) {
            if (commandStringCounter != 0) {
                commandStringCounter = commandStringCounter - 1;
            } 
            jTextFieldOut.setText(commandString[commandStringCounter]);
            
        }
        if (evt.getKeyCode()== 40) {
            if (commandStringCounter < commandString.length) {
                commandStringCounter = commandStringCounter + 1;
            } 
            jTextFieldOut.setText(commandString[commandStringCounter]);
            //jTextFieldOut.setText(str);
            
        }
        */
    }//GEN-LAST:event_jTextFieldOutKeyPressed

    private class Reader implements SerialPortEventListener { //Класс Reader реализует интерфейс SerialPortEventListener

        private String str = "";

        /*
        Метод serialEvent принимает в качестве параметра переменную типа SerialPortEvent
        */
        public void serialEvent(SerialPortEvent spe) {
            if(spe.isRXCHAR() || spe.isRXFLAG()){ //Если установлены флаги RXCHAR и  RXFLAG                
                if(spe.getEventValue() > 0){ //Если число байт во входном буффере больше 0, то
                    try {
                        str = "";
                        byte[] buffer = serialPort.readBytes(spe.getEventValue()); //читаем из COM-порта необходимое число байт 
                        if(jComboBoxIn.getSelectedIndex() == 0){// Если выбран метод отображения "Chars"
                            str = new String(buffer); //преобразуем байты в строку символов 
                        }
                        else if(jComboBoxIn.getSelectedIndex() == 1 || jComboBoxIn.getSelectedIndex() == 2){// иначе, если выбран метод отображения Digits and HEX
                            int[] intBuffer = new int[buffer.length]; // создаем массив целых чисел intBuffer необходимого размера  
                            for(int i = 0; i < buffer.length; i++){ //переносим считанные данные в intBuffer 
                                if(buffer[i] < 0){
                                    intBuffer[i] = 256 + buffer[i];
                                }
                                else {
                                    intBuffer[i] = buffer[i];
                                }
                            }
                            for(int i : intBuffer){
                                if(jComboBoxIn.getSelectedIndex() == 2){ // Если выбран метод отображения "HEX"
                                    String value = Integer.toHexString(i).toUpperCase();
                                    if(value.length() == 1) {
                                        value = "0" + value;
                                    }
                                    str += (value + " ");
                                }
                                else {
                                    str += (i + " ");
                                }
                            }
                        }
                        SwingUtilities.invokeAndWait(
                            new Runnable() {
                                public void run() {
                                    jTextAreaIn.append(str);
                                    int scrollValue = jScrollPaneIn.getVerticalScrollBar().getValue();
                                    int scrollBottom = jScrollPaneIn.getVerticalScrollBar().getMaximum() - jScrollPaneIn.getVerticalScrollBar().getVisibleAmount();
                                    if((scrollValue == scrollBottom) && (scrollValue > 0)){
                                        jTextAreaIn.setCaretPosition(jTextAreaIn.getText().length());
                                    }
                                }
                            }
                        );
                    }
                    catch (Exception ex) {
                        DialogMessage dialogMessage = new DialogMessage(NewJFrame.this, DialogMessage.TYPE_ERROR, "Ошибка", ex.getMessage());
                        
                        //Do nothing
                    }
                    
                }
            }
            else if(spe.isCTS()){
                if(spe.getEventValue() == 1){
                    jLabelCTS.setBorder(NimbusGui.borderStatusOn);
                    jLabelCTS.setBackground(NimbusGui.colorStatusOnBG);
                }
                else {
                    jLabelCTS.setBorder(NimbusGui.borderStatusOff);
                    jLabelCTS.setBackground(NimbusGui.colorStatusOffBG);
                }
            }
            else if(spe.isDSR()){
                if(spe.getEventValue() == 1){
                    jLabelDSR.setBorder(NimbusGui.borderStatusOn);
                    jLabelDSR.setBackground(NimbusGui.colorStatusOnBG);
                }
                else {
                    jLabelDSR.setBorder(NimbusGui.borderStatusOff);
                    jLabelDSR.setBackground(NimbusGui.colorStatusOffBG);
                }
            }
            else if(spe.isRLSD()){
                if(spe.getEventValue() == 1){
                    jLabelRLSD.setBorder(NimbusGui.borderStatusOn);
                    jLabelRLSD.setBackground(NimbusGui.colorStatusOnBG);
                }
                else {
                    jLabelRLSD.setBorder(NimbusGui.borderStatusOff);
                    jLabelRLSD.setBackground(NimbusGui.colorStatusOffBG);
                }
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels=javax.swing.UIManager.getInstalledLookAndFeels();
            for (int idx=0; idx<installedLookAndFeels.length; idx++)
                if ("Nimbus".equals(installedLookAndFeels[idx].getName())) {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
                    break;
                }
        }
         catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true); // Создаем объект NewJFrame() и делаем его видимым                
            }            
        });
                
        */
        new NewJFrame().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jAppName;
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonOpenPort;
    private javax.swing.JButton jButtonSend;
    private javax.swing.JButton jButtonSendHEX;
    private javax.swing.JButton jButtonSettings;
    private javax.swing.JComboBox jComboBoxIn;
    private javax.swing.JLabel jHeaderLabelInput;
    private javax.swing.JLabel jHeaderLabelOutput;
    private javax.swing.JPanel jHeaderPanelInput;
    private javax.swing.JPanel jHeaderPanelOutput;
    private javax.swing.JPanel jInputDataPanel;
    private javax.swing.JLabel jLabelCTS;
    private javax.swing.JLabel jLabelDSR;
    private javax.swing.JLabel jLabelPortInfo;
    private javax.swing.JLabel jLabelRLSD;
    private javax.swing.JLabel jLabelView;
    private javax.swing.JPanel jOutputDataPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPaneIn;
    private javax.swing.JSeparator jSeparatorOut;
    private javax.swing.JTextArea jTextAreaIn;
    private javax.swing.JTextField jTextFieldOut;
    private javax.swing.JToggleButton jToggleButtonDTR;
    private javax.swing.JToggleButton jToggleButtonRTS;
    // End of variables declaration//GEN-END:variables
}
