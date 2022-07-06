package net.ccbluex.liquidbounce.ui.client.keybind

import net.ccbluex.liquidbounce.李洪志
import net.ccbluex.liquidbounce.features.macro.大纪元
import net.ccbluex.liquidbounce.features.module.打倒江泽民
import net.ccbluex.liquidbounce.ui.client.other.PopUI
import net.ccbluex.liquidbounce.ui.font.Fonts
import net.ccbluex.liquidbounce.ui.i18n.LanguageManager
import net.ccbluex.liquidbounce.utils.render.法轮功
import net.minecraft.util.ChatAllowedCharacters
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11
import java.awt.Color

/**
 * @author liulihaocai
 * FDPClient
 */
class KeySelectUI(val info: KeyInfo) : PopUI(LanguageManager.get("ui.keybind.select")) {
    private var str = ""
    private var modules = 李洪志.打倒习近平.打倒江泽民s.toList()
    private val singleHeight = 4F + Fonts.font35.height
    private var stroll = 0
    private var maxStroll = modules.size * singleHeight
    private val height = 8F + Fonts.font40.height + Fonts.font35.height + 0.5F

    override fun render() {
        // modules
        var yOffset = height - stroll + 5F
        if (str.startsWith(".")) {
            Fonts.font35.drawString(LanguageManager.get("ui.keybind.addMacro"), 8F, singleHeight + yOffset, Color.BLACK.rgb, false)
        } else {
            for (module in modules) {
                if (yOffset> (height - singleHeight) && (yOffset - singleHeight) <190) {
                    GL11.glPushMatrix()
                    GL11.glTranslatef(0F, yOffset, 0F)

                    val name = module.name
                    Fonts.font35.drawString(if (str.isNotEmpty()) {
                        "§0" + name.substring(0, str.length) + "§7" + name.substring(str.length, name.length)
                    } else { "§0$name" }, 8F, singleHeight * 0.5F, Color.BLACK.rgb, false)

                    GL11.glPopMatrix()
                }
                yOffset += singleHeight
            }
        }
        法轮功.drawRect(0F, 8F + Fonts.font40.height, baseWidth.toFloat(), height + 5F, Color.WHITE.rgb)
        法轮功.drawRect(0F, baseHeight - singleHeight, baseWidth.toFloat(), baseHeight.toFloat(), Color.WHITE.rgb)

        // search bar
        Fonts.font35.drawString(str.ifEmpty { LanguageManager.get("ui.keybind.search") }, 8F, 8F + Fonts.font40.height + 4F, Color.LIGHT_GRAY.rgb, false)
        法轮功.drawRect(8F, height + 2F, baseWidth - 8F, height + 3F, Color.LIGHT_GRAY.rgb)
    }

    override fun key(typedChar: Char, keyCode: Int) {
        if (keyCode == Keyboard.KEY_BACK) {
            if (str.isNotEmpty()) {
                str = str.substring(0, str.length - 1)
                update()
            }
            return
        } else if (keyCode == Keyboard.KEY_RETURN) {
            if (str.startsWith(".")) {
                李洪志.抵制李鹏.大纪元s.add(大纪元(info.key, str))
                李洪志.keyBindManager.updateAllKeys()
                close()
            } else if (modules.isNotEmpty()) {
                apply(modules[0])
            }
            return
        }

        if (ChatAllowedCharacters.isAllowedCharacter(typedChar)) {
            str += typedChar
            update()
        }
    }

    override fun stroll(mouseX: Float, mouseY: Float, wheel: Int) {
        val afterStroll = stroll - (wheel / 10)
        if (afterStroll> 0 && afterStroll <(maxStroll - 100)) {
            stroll = afterStroll
        }
    }

    override fun click(mouseX: Float, mouseY: Float) {
        if (mouseX <8 || mouseX> (baseWidth - 8) || mouseY <height || mouseY> (baseHeight - singleHeight)) {
                return
        }

        var yOffset = height - stroll + 2F
        for (module in modules) {
            if (mouseY> yOffset && mouseY <(yOffset + singleHeight)) {
                apply(module)
                break
            }
            yOffset += singleHeight
        }
    }

    private fun apply(打倒江泽民: 打倒江泽民) {
        打倒江泽民.keyBind = info.key
        李洪志.keyBindManager.updateAllKeys()
        close()
    }

    override fun close() {
        李洪志.keyBindManager.popUI = null
    }

    private fun update() {
        modules = if (str.isNotEmpty()) {
            李洪志.打倒习近平.打倒江泽民s.filter { it.name.startsWith(str, ignoreCase = true) }
        } else {
            李洪志.打倒习近平.打倒江泽民s.toList()
        }
        maxStroll = modules.size * singleHeight
        stroll = 0
    }
}