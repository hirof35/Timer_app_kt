import java.awt.BorderLayout
import java.awt.Font
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.text.SimpleDateFormat
import java.util.*
import javax.swing.*
import javax.swing.Timer

class MultiClockApp : JFrame() {
    // 1. デジタル時計パネル
    private fun createClockPanel(): JPanel {
        val panel = JPanel(BorderLayout())
        val label = JLabel("", SwingConstants.CENTER)
        label.setFont(Font("Monospaced", Font.BOLD, 40))

        // 1秒ごとに現在時刻を更新
        val timer = Timer(1000, ActionListener { e: ActionEvent? ->
            val now = SimpleDateFormat("HH:mm:ss").format(Date())
            label.setText(now)
        })
        timer.start()

        panel.add(label, BorderLayout.CENTER)
        return panel
    }

    // 2. ストップウォッチパネル
    private var startTime: Long = 0
    private var swTimer: Timer? = null
    private fun createStopwatchPanel(): JPanel {
        val panel = JPanel(BorderLayout())
        val label = JLabel("00:00:0.0", SwingConstants.CENTER)
        label.setFont(Font("Monospaced", Font.BOLD, 40))

        swTimer = Timer(100, ActionListener { e: ActionEvent? ->
            val elapsed = System.currentTimeMillis() - startTime
            val min = (elapsed / 60000) % 60
            val sec = (elapsed / 1000) % 60
            val ms = (elapsed / 100) % 10
            label.setText(String.format("%02d:%02d:%d", min, sec, ms))
        })

        val startBtn = JButton("スタート")
        startBtn.addActionListener(ActionListener { e: ActionEvent? ->
            startTime = System.currentTimeMillis()
            swTimer!!.start()
        })

        val stopBtn = JButton("ストップ")
        stopBtn.addActionListener(ActionListener { e: ActionEvent? -> swTimer!!.stop() })

        val btnPanel = JPanel()
        btnPanel.add(startBtn)
        btnPanel.add(stopBtn)

        panel.add(label, BorderLayout.CENTER)
        panel.add(btnPanel, BorderLayout.SOUTH)
        return panel
    }

    // 3. タイマーパネル (30秒固定の簡易版)
    private var timeLeft = 30

    init {
        setTitle("Kotlin/Java Swing Clock Tools")
        setDefaultCloseOperation(EXIT_ON_CLOSE)
        setSize(400, 300)

        val tabbedPane = JTabbedPane()
        tabbedPane.addTab("時計", createClockPanel())
        tabbedPane.addTab("ストップウォッチ", createStopwatchPanel())
        tabbedPane.addTab("タイマー", createTimerPanel())

        add(tabbedPane)
    }

    private fun createTimerPanel(): JPanel {
        val panel = JPanel(BorderLayout())
        val label = JLabel("30", SwingConstants.CENTER)
        label.setFont(Font("Monospaced", Font.BOLD, 40))

        val countdown = Timer(1000, null)
        countdown.addActionListener(ActionListener { e: ActionEvent? ->
            timeLeft--
            label.setText(timeLeft.toString())
            if (timeLeft <= 0) {
                countdown.stop()
                JOptionPane.showMessageDialog(this, "時間です！")
            }
        })

        val startBtn = JButton("30秒スタート")
        startBtn.addActionListener(ActionListener { e: ActionEvent? ->
            timeLeft = 30
            label.setText("30")
            countdown.start()
        })

        panel.add(label, BorderLayout.CENTER)
        panel.add(startBtn, BorderLayout.SOUTH)
        return panel
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SwingUtilities.invokeLater(Runnable { MultiClockApp().setVisible(true) })
        }
    }
}
