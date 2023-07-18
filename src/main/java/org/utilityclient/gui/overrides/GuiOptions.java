package org.utilityclient.gui.overrides;

import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.screen.options.ChatOptionsScreen;
import net.minecraft.client.gui.screen.options.ControlsOptionsScreen;
import net.minecraft.client.gui.screen.options.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.options.SkinOptionsScreen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.Difficulty;
import org.utilityclient.UtilityClient;
import org.utilityclient.gui.UCScreen;
import org.utilityclient.gui.options.GuiUtilityClient;

public class GuiOptions extends UCScreen implements IdentifibleBooleanConsumer {
    private static final GameOptions.Option[] OPTIONS;
    private final Screen parent;
    private final GameOptions options;
    private ButtonWidget difficultyButton;
    private LockButtonWidget lockDifficultyButton;
    protected String title = "Options";

    public GuiOptions(Screen parent, GameOptions options) {
        super("Changing settings");
        this.parent = parent;
        this.options = options;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void init()
    {
        int i = 0;
        this.title = I18n.translate("options.title");
        GameOptions.Option[] options = OPTIONS;
        int j = options.length;

        for(int k = 0; k < j; ++k) {
            GameOptions.Option option = options[k];
            if (option.isNumeric()) {
                this.buttons.add(new OptionSliderWidget(option.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), option));
            } else {
                OptionButtonWidget optionButtonWidget = new OptionButtonWidget(option.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), option, this.options.getValueMessage(option));
                this.buttons.add(optionButtonWidget);
            }

            ++i;
        }

        if (this.client.world != null) {
            Difficulty difficulty = this.client.world.getGlobalDifficulty();
            this.difficultyButton = new ButtonWidget(108, this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), 150, 20, this.getDifficultyButtonText(difficulty));
            this.buttons.add(this.difficultyButton);
            if (this.client.isInSingleplayer() && !this.client.world.getLevelProperties().isHardcore()) {
                this.difficultyButton.setWidth(this.difficultyButton.getWidth() - 20);
                this.lockDifficultyButton = new LockButtonWidget(109, this.difficultyButton.x + this.difficultyButton.getWidth(), this.difficultyButton.y);
                this.buttons.add(this.lockDifficultyButton);
                this.lockDifficultyButton.setLocked(this.client.world.getLevelProperties().isDifficultyLocked());
                this.lockDifficultyButton.active = !this.lockDifficultyButton.isLocked();
                this.difficultyButton.active = !this.lockDifficultyButton.isLocked();
            } else {
                this.difficultyButton.active = false;
            }
        } else {
            OptionButtonWidget optionButtonWidget2 = new OptionButtonWidget(GameOptions.Option.REALMS_NOTIFICATIONS.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), GameOptions.Option.REALMS_NOTIFICATIONS, this.options.getValueMessage(GameOptions.Option.REALMS_NOTIFICATIONS));
            this.buttons.add(optionButtonWidget2);
        }

        buttons.add(new ButtonWidget(99,   this.width / 2 + 5,     this.height / 6 + 48 - 6, 150, 20, UtilityClient.getClientName() + "..."));
        buttons.add(new ButtonWidget(100,  this.width / 2 - 155,   this.height / 6 + 48 - 6, 150, 20, I18n.translate("options.controls")));

        buttons.add(new ButtonWidget(106,  this.width / 2 + 5,     this.height / 6 + 72 - 6, 150, 20, I18n.translate("options.sounds")));
        buttons.add(new ButtonWidget(101,  this.width / 2 - 155,   this.height / 6 + 72 - 6, 150, 20, I18n.translate("options.video")));

        buttons.add(new ButtonWidget(103,  this.width / 2 + 5,     this.height / 6 + 96 - 6, 150, 20, I18n.translate("options.chat.title")));
        buttons.add(new ButtonWidget(102,  this.width / 2 - 155,   this.height / 6 + 96 - 6, 150, 20, I18n.translate("options.language")));

        buttons.add(new ButtonWidget(110,  this.width / 2 + 5,     this.height / 6 + 120 - 6,150, 20, I18n.translate("options.skinCustomisation")));
        buttons.add(new ButtonWidget(105,  this.width / 2 - 155,   this.height / 6 + 120 - 6,150, 20, I18n.translate("options.resourcepack")));

        buttons.add(new ButtonWidget(200,  this.width / 2 - 100,   this.height / 6 + 168,                            I18n.translate("gui.done")));
    }

    public String getDifficultyButtonText(Difficulty difficulty) {
        Text text = new LiteralText("");
        text.append(new TranslatableText("options.difficulty"));
        text.append(": ");
        text.append(new TranslatableText(difficulty.getName()));
        return text.asFormattedString();
    }

    public void confirmResult(boolean b, int id) {
        this.client.openScreen(this);
        if (id == 109 && b && this.client.world != null) {
            this.client.world.getLevelProperties().setDifficultyLocked(true);
            this.lockDifficultyButton.setLocked(true);
            this.lockDifficultyButton.active = false;
            this.difficultyButton.active = false;
        }

    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void buttonClicked(ButtonWidget button)
    {
        if (button.active)
        {
            if (button.id < 100 && button instanceof OptionButtonWidget) {
                GameOptions.Option option = ((OptionButtonWidget)button).getOption();
                this.options.getBooleanValue(option, 1);
                button.message = this.options.getValueMessage(GameOptions.Option.byOrdinal(button.id));
            }

            if (button.id == 108) {
                this.client.world.getLevelProperties().setDifficulty(Difficulty.byOrdinal(this.client.world.getGlobalDifficulty().getId() + 1));
                this.difficultyButton.message = this.getDifficultyButtonText(this.client.world.getGlobalDifficulty());
            }

            if (button.id == 109) {
                this.client.openScreen(new ConfirmScreen(this, (new TranslatableText("difficulty.lock.title", new Object[0])).asFormattedString(), (new TranslatableText("difficulty.lock.question", new Object[]{new TranslatableText(this.client.world.getLevelProperties().getDifficulty().getName(), new Object[0])})).asFormattedString(), 109));
            }

            if (button.id == 110) {
                this.client.options.save();
                this.client.openScreen(new SkinOptionsScreen(this));
            }

            if (button.id == 99)
            {
                client.options.save();
                client.openScreen(new GuiUtilityClient(this));
            }

            if (button.id == 101) {
                this.client.options.save();
                this.client.openScreen(new VideoOptionsScreen(this, this.options));
            }

            if (button.id == 100) {
                this.client.options.save();
                this.client.openScreen(new ControlsOptionsScreen(this, this.options));
            }

            if (button.id == 102) {
                this.client.options.save();
                this.client.openScreen(new LanguageOptionsScreen(this, this.options, this.client.getLanguageManager()));
            }

            if (button.id == 103) {
                this.client.options.save();
                this.client.openScreen(new ChatOptionsScreen(this, this.options));
            }

            if (button.id == 104) {
                this.client.options.save();
                this.client.openScreen(new SnooperScreen(this, this.options));
            }

            if (button.id == 200) {
                this.client.options.save();
                this.client.openScreen(this.parent);
            }

            if (button.id == 105) {
                this.client.options.save();
                this.client.openScreen(new ResourcePackScreen(this));
            }

            if (button.id == 106) {
                this.client.options.save();
                this.client.openScreen(new SoundsScreen(this, this.options));
            }

            if (button.id == 107)
            {
                client.options.save();
            }
        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void render(int mouseX, int mouseY, float tickDelta) {
        this.renderBackground();
        this.drawCenteredString(this.textRenderer, this.title, this.width / 2, 15, 16777215);
        super.render(mouseX, mouseY, tickDelta);
    }

    static {
        OPTIONS = new GameOptions.Option[]{GameOptions.Option.FIELD_OF_VIEW};
    }
}
