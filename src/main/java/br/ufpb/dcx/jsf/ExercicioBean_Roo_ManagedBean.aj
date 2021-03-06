// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.dcx.jsf;

import br.ufpb.dcx.jsf.ExercicioBean;
import br.ufpb.dcx.jsf.util.MessageFactory;
import br.ufpb.dcx.model.PerfilEnum;
import br.ufpb.dcx.model.Usuario;
import br.ufpb.dcx.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;

privileged aspect ExercicioBean_Roo_ManagedBean {
    
    declare @type: ExercicioBean: @ManagedBean(name = "exercicioBean");
    
    declare @type: ExercicioBean: @SessionScoped;
    
    @Autowired
    UsuarioService ExercicioBean.usuarioService;
    
    private String ExercicioBean.name = "Usuarios";
    
    private Usuario ExercicioBean.usuario;
    
    private List<Usuario> ExercicioBean.allUsuarios;
    
    private boolean ExercicioBean.dataVisible = false;
    
    private List<String> ExercicioBean.columns;
    
    private HtmlPanelGrid ExercicioBean.createPanelGrid;
    
    private HtmlPanelGrid ExercicioBean.editPanelGrid;
    
    private HtmlPanelGrid ExercicioBean.viewPanelGrid;
    
    private boolean ExercicioBean.createDialogVisible = false;
    
    @PostConstruct
    public void ExercicioBean.init() {
        columns = new ArrayList<String>();
        columns.add("nome");
        columns.add("nickName");
        columns.add("email");
        columns.add("senha");
        columns.add("senhaDeRecuperacao");
    }
    
    public String ExercicioBean.getName() {
        return name;
    }
    
    public List<String> ExercicioBean.getColumns() {
        return columns;
    }
    
    public List<Usuario> ExercicioBean.getAllUsuarios() {
        return allUsuarios;
    }
    
    public void ExercicioBean.setAllUsuarios(List<Usuario> allUsuarios) {
        this.allUsuarios = allUsuarios;
    }
    
    public String ExercicioBean.findAllUsuarios() {
        allUsuarios = usuarioService.findAllUsuarios();
        dataVisible = !allUsuarios.isEmpty();
        return null;
    }
    
    public boolean ExercicioBean.isDataVisible() {
        return dataVisible;
    }
    
    public void ExercicioBean.setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }
    
    public HtmlPanelGrid ExercicioBean.getCreatePanelGrid() {
        if (createPanelGrid == null) {
            createPanelGrid = populateCreatePanel();
        }
        return createPanelGrid;
    }
    
    public void ExercicioBean.setCreatePanelGrid(HtmlPanelGrid createPanelGrid) {
        this.createPanelGrid = createPanelGrid;
    }
    
    public HtmlPanelGrid ExercicioBean.getEditPanelGrid() {
        if (editPanelGrid == null) {
            editPanelGrid = populateEditPanel();
        }
        return editPanelGrid;
    }
    
    public void ExercicioBean.setEditPanelGrid(HtmlPanelGrid editPanelGrid) {
        this.editPanelGrid = editPanelGrid;
    }
    
    public HtmlPanelGrid ExercicioBean.getViewPanelGrid() {
        return populateViewPanel();
    }
    
    public void ExercicioBean.setViewPanelGrid(HtmlPanelGrid viewPanelGrid) {
        this.viewPanelGrid = viewPanelGrid;
    }
    
    public HtmlPanelGrid ExercicioBean.populateCreatePanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel nomeCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        nomeCreateOutput.setFor("nomeCreateInput");
        nomeCreateOutput.setId("nomeCreateOutput");
        nomeCreateOutput.setValue("Nome:");
        htmlPanelGrid.getChildren().add(nomeCreateOutput);
        
        InputText nomeCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        nomeCreateInput.setId("nomeCreateInput");
        nomeCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.nome}", String.class));
        nomeCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(nomeCreateInput);
        
        Message nomeCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nomeCreateInputMessage.setId("nomeCreateInputMessage");
        nomeCreateInputMessage.setFor("nomeCreateInput");
        nomeCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nomeCreateInputMessage);
        
        OutputLabel nickNameCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        nickNameCreateOutput.setFor("nickNameCreateInput");
        nickNameCreateOutput.setId("nickNameCreateOutput");
        nickNameCreateOutput.setValue("Nick Name:");
        htmlPanelGrid.getChildren().add(nickNameCreateOutput);
        
        InputText nickNameCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        nickNameCreateInput.setId("nickNameCreateInput");
        nickNameCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.nickName}", String.class));
        nickNameCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(nickNameCreateInput);
        
        Message nickNameCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nickNameCreateInputMessage.setId("nickNameCreateInputMessage");
        nickNameCreateInputMessage.setFor("nickNameCreateInput");
        nickNameCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nickNameCreateInputMessage);
        
        OutputLabel emailCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        emailCreateOutput.setFor("emailCreateInput");
        emailCreateOutput.setId("emailCreateOutput");
        emailCreateOutput.setValue("Email:");
        htmlPanelGrid.getChildren().add(emailCreateOutput);
        
        InputText emailCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        emailCreateInput.setId("emailCreateInput");
        emailCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.email}", String.class));
        emailCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(emailCreateInput);
        
        Message emailCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        emailCreateInputMessage.setId("emailCreateInputMessage");
        emailCreateInputMessage.setFor("emailCreateInput");
        emailCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(emailCreateInputMessage);
        
        OutputLabel senhaCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        senhaCreateOutput.setFor("senhaCreateInput");
        senhaCreateOutput.setId("senhaCreateOutput");
        senhaCreateOutput.setValue("Senha:");
        htmlPanelGrid.getChildren().add(senhaCreateOutput);
        
        InputText senhaCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        senhaCreateInput.setId("senhaCreateInput");
        senhaCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.senha}", String.class));
        senhaCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(senhaCreateInput);
        
        Message senhaCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        senhaCreateInputMessage.setId("senhaCreateInputMessage");
        senhaCreateInputMessage.setFor("senhaCreateInput");
        senhaCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(senhaCreateInputMessage);
        
        OutputLabel senhaDeRecuperacaoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        senhaDeRecuperacaoCreateOutput.setFor("senhaDeRecuperacaoCreateInput");
        senhaDeRecuperacaoCreateOutput.setId("senhaDeRecuperacaoCreateOutput");
        senhaDeRecuperacaoCreateOutput.setValue("Senha De Recuperacao:");
        htmlPanelGrid.getChildren().add(senhaDeRecuperacaoCreateOutput);
        
        InputText senhaDeRecuperacaoCreateInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        senhaDeRecuperacaoCreateInput.setId("senhaDeRecuperacaoCreateInput");
        senhaDeRecuperacaoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.senhaDeRecuperacao}", String.class));
        senhaDeRecuperacaoCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(senhaDeRecuperacaoCreateInput);
        
        Message senhaDeRecuperacaoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        senhaDeRecuperacaoCreateInputMessage.setId("senhaDeRecuperacaoCreateInputMessage");
        senhaDeRecuperacaoCreateInputMessage.setFor("senhaDeRecuperacaoCreateInput");
        senhaDeRecuperacaoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(senhaDeRecuperacaoCreateInputMessage);
        
        OutputLabel isAtivoCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        isAtivoCreateOutput.setFor("isAtivoCreateInput");
        isAtivoCreateOutput.setId("isAtivoCreateOutput");
        isAtivoCreateOutput.setValue("Is Ativo:");
        htmlPanelGrid.getChildren().add(isAtivoCreateOutput);
        
        SelectBooleanCheckbox isAtivoCreateInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        isAtivoCreateInput.setId("isAtivoCreateInput");
        isAtivoCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.isAtivo}", Boolean.class));
        isAtivoCreateInput.setRequired(false);
        htmlPanelGrid.getChildren().add(isAtivoCreateInput);
        
        Message isAtivoCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        isAtivoCreateInputMessage.setId("isAtivoCreateInputMessage");
        isAtivoCreateInputMessage.setFor("isAtivoCreateInput");
        isAtivoCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(isAtivoCreateInputMessage);
        
        OutputLabel perfilCreateOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        perfilCreateOutput.setFor("perfilCreateInput");
        perfilCreateOutput.setId("perfilCreateOutput");
        perfilCreateOutput.setValue("Perfil:");
        htmlPanelGrid.getChildren().add(perfilCreateOutput);
        
        AutoComplete perfilCreateInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        perfilCreateInput.setId("perfilCreateInput");
        perfilCreateInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.perfil}", PerfilEnum.class));
        perfilCreateInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{exercicioBean.completePerfil}", List.class, new Class[] { String.class }));
        perfilCreateInput.setDropdown(true);
        perfilCreateInput.setRequired(true);
        htmlPanelGrid.getChildren().add(perfilCreateInput);
        
        Message perfilCreateInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        perfilCreateInputMessage.setId("perfilCreateInputMessage");
        perfilCreateInputMessage.setFor("perfilCreateInput");
        perfilCreateInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(perfilCreateInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid ExercicioBean.populateEditPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        OutputLabel nomeEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        nomeEditOutput.setFor("nomeEditInput");
        nomeEditOutput.setId("nomeEditOutput");
        nomeEditOutput.setValue("Nome:");
        htmlPanelGrid.getChildren().add(nomeEditOutput);
        
        InputText nomeEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        nomeEditInput.setId("nomeEditInput");
        nomeEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.nome}", String.class));
        nomeEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(nomeEditInput);
        
        Message nomeEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nomeEditInputMessage.setId("nomeEditInputMessage");
        nomeEditInputMessage.setFor("nomeEditInput");
        nomeEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nomeEditInputMessage);
        
        OutputLabel nickNameEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        nickNameEditOutput.setFor("nickNameEditInput");
        nickNameEditOutput.setId("nickNameEditOutput");
        nickNameEditOutput.setValue("Nick Name:");
        htmlPanelGrid.getChildren().add(nickNameEditOutput);
        
        InputText nickNameEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        nickNameEditInput.setId("nickNameEditInput");
        nickNameEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.nickName}", String.class));
        nickNameEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(nickNameEditInput);
        
        Message nickNameEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        nickNameEditInputMessage.setId("nickNameEditInputMessage");
        nickNameEditInputMessage.setFor("nickNameEditInput");
        nickNameEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(nickNameEditInputMessage);
        
        OutputLabel emailEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        emailEditOutput.setFor("emailEditInput");
        emailEditOutput.setId("emailEditOutput");
        emailEditOutput.setValue("Email:");
        htmlPanelGrid.getChildren().add(emailEditOutput);
        
        InputText emailEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        emailEditInput.setId("emailEditInput");
        emailEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.email}", String.class));
        emailEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(emailEditInput);
        
        Message emailEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        emailEditInputMessage.setId("emailEditInputMessage");
        emailEditInputMessage.setFor("emailEditInput");
        emailEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(emailEditInputMessage);
        
        OutputLabel senhaEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        senhaEditOutput.setFor("senhaEditInput");
        senhaEditOutput.setId("senhaEditOutput");
        senhaEditOutput.setValue("Senha:");
        htmlPanelGrid.getChildren().add(senhaEditOutput);
        
        InputText senhaEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        senhaEditInput.setId("senhaEditInput");
        senhaEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.senha}", String.class));
        senhaEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(senhaEditInput);
        
        Message senhaEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        senhaEditInputMessage.setId("senhaEditInputMessage");
        senhaEditInputMessage.setFor("senhaEditInput");
        senhaEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(senhaEditInputMessage);
        
        OutputLabel senhaDeRecuperacaoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        senhaDeRecuperacaoEditOutput.setFor("senhaDeRecuperacaoEditInput");
        senhaDeRecuperacaoEditOutput.setId("senhaDeRecuperacaoEditOutput");
        senhaDeRecuperacaoEditOutput.setValue("Senha De Recuperacao:");
        htmlPanelGrid.getChildren().add(senhaDeRecuperacaoEditOutput);
        
        InputText senhaDeRecuperacaoEditInput = (InputText) application.createComponent(InputText.COMPONENT_TYPE);
        senhaDeRecuperacaoEditInput.setId("senhaDeRecuperacaoEditInput");
        senhaDeRecuperacaoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.senhaDeRecuperacao}", String.class));
        senhaDeRecuperacaoEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(senhaDeRecuperacaoEditInput);
        
        Message senhaDeRecuperacaoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        senhaDeRecuperacaoEditInputMessage.setId("senhaDeRecuperacaoEditInputMessage");
        senhaDeRecuperacaoEditInputMessage.setFor("senhaDeRecuperacaoEditInput");
        senhaDeRecuperacaoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(senhaDeRecuperacaoEditInputMessage);
        
        OutputLabel isAtivoEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        isAtivoEditOutput.setFor("isAtivoEditInput");
        isAtivoEditOutput.setId("isAtivoEditOutput");
        isAtivoEditOutput.setValue("Is Ativo:");
        htmlPanelGrid.getChildren().add(isAtivoEditOutput);
        
        SelectBooleanCheckbox isAtivoEditInput = (SelectBooleanCheckbox) application.createComponent(SelectBooleanCheckbox.COMPONENT_TYPE);
        isAtivoEditInput.setId("isAtivoEditInput");
        isAtivoEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.isAtivo}", Boolean.class));
        isAtivoEditInput.setRequired(false);
        htmlPanelGrid.getChildren().add(isAtivoEditInput);
        
        Message isAtivoEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        isAtivoEditInputMessage.setId("isAtivoEditInputMessage");
        isAtivoEditInputMessage.setFor("isAtivoEditInput");
        isAtivoEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(isAtivoEditInputMessage);
        
        OutputLabel perfilEditOutput = (OutputLabel) application.createComponent(OutputLabel.COMPONENT_TYPE);
        perfilEditOutput.setFor("perfilEditInput");
        perfilEditOutput.setId("perfilEditOutput");
        perfilEditOutput.setValue("Perfil:");
        htmlPanelGrid.getChildren().add(perfilEditOutput);
        
        AutoComplete perfilEditInput = (AutoComplete) application.createComponent(AutoComplete.COMPONENT_TYPE);
        perfilEditInput.setId("perfilEditInput");
        perfilEditInput.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.perfil}", PerfilEnum.class));
        perfilEditInput.setCompleteMethod(expressionFactory.createMethodExpression(elContext, "#{exercicioBean.completePerfil}", List.class, new Class[] { String.class }));
        perfilEditInput.setDropdown(true);
        perfilEditInput.setRequired(true);
        htmlPanelGrid.getChildren().add(perfilEditInput);
        
        Message perfilEditInputMessage = (Message) application.createComponent(Message.COMPONENT_TYPE);
        perfilEditInputMessage.setId("perfilEditInputMessage");
        perfilEditInputMessage.setFor("perfilEditInput");
        perfilEditInputMessage.setDisplay("icon");
        htmlPanelGrid.getChildren().add(perfilEditInputMessage);
        
        return htmlPanelGrid;
    }
    
    public HtmlPanelGrid ExercicioBean.populateViewPanel() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        javax.faces.application.Application application = facesContext.getApplication();
        ExpressionFactory expressionFactory = application.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        
        HtmlPanelGrid htmlPanelGrid = (HtmlPanelGrid) application.createComponent(HtmlPanelGrid.COMPONENT_TYPE);
        
        HtmlOutputText nomeLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nomeLabel.setId("nomeLabel");
        nomeLabel.setValue("Nome:");
        htmlPanelGrid.getChildren().add(nomeLabel);
        
        HtmlOutputText nomeValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nomeValue.setId("nomeValue");
        nomeValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.nome}", String.class));
        htmlPanelGrid.getChildren().add(nomeValue);
        
        HtmlOutputText nickNameLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nickNameLabel.setId("nickNameLabel");
        nickNameLabel.setValue("Nick Name:");
        htmlPanelGrid.getChildren().add(nickNameLabel);
        
        HtmlOutputText nickNameValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        nickNameValue.setId("nickNameValue");
        nickNameValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.nickName}", String.class));
        htmlPanelGrid.getChildren().add(nickNameValue);
        
        HtmlOutputText emailLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        emailLabel.setId("emailLabel");
        emailLabel.setValue("Email:");
        htmlPanelGrid.getChildren().add(emailLabel);
        
        HtmlOutputText emailValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        emailValue.setId("emailValue");
        emailValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.email}", String.class));
        htmlPanelGrid.getChildren().add(emailValue);
        
        HtmlOutputText senhaLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        senhaLabel.setId("senhaLabel");
        senhaLabel.setValue("Senha:");
        htmlPanelGrid.getChildren().add(senhaLabel);
        
        HtmlOutputText senhaValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        senhaValue.setId("senhaValue");
        senhaValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.senha}", String.class));
        htmlPanelGrid.getChildren().add(senhaValue);
        
        HtmlOutputText senhaDeRecuperacaoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        senhaDeRecuperacaoLabel.setId("senhaDeRecuperacaoLabel");
        senhaDeRecuperacaoLabel.setValue("Senha De Recuperacao:");
        htmlPanelGrid.getChildren().add(senhaDeRecuperacaoLabel);
        
        HtmlOutputText senhaDeRecuperacaoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        senhaDeRecuperacaoValue.setId("senhaDeRecuperacaoValue");
        senhaDeRecuperacaoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.senhaDeRecuperacao}", String.class));
        htmlPanelGrid.getChildren().add(senhaDeRecuperacaoValue);
        
        HtmlOutputText isAtivoLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        isAtivoLabel.setId("isAtivoLabel");
        isAtivoLabel.setValue("Is Ativo:");
        htmlPanelGrid.getChildren().add(isAtivoLabel);
        
        HtmlOutputText isAtivoValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        isAtivoValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.isAtivo}", String.class));
        htmlPanelGrid.getChildren().add(isAtivoValue);
        
        HtmlOutputText perfilLabel = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        perfilLabel.setId("perfilLabel");
        perfilLabel.setValue("Perfil:");
        htmlPanelGrid.getChildren().add(perfilLabel);
        
        HtmlOutputText perfilValue = (HtmlOutputText) application.createComponent(HtmlOutputText.COMPONENT_TYPE);
        perfilValue.setValueExpression("value", expressionFactory.createValueExpression(elContext, "#{exercicioBean.usuario.perfil}", String.class));
        htmlPanelGrid.getChildren().add(perfilValue);
        
        return htmlPanelGrid;
    }
    
    public Usuario ExercicioBean.getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }
    
    public void ExercicioBean.setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<PerfilEnum> ExercicioBean.completePerfil(String query) {
        List<PerfilEnum> suggestions = new ArrayList<PerfilEnum>();
        for (PerfilEnum perfilEnum : PerfilEnum.values()) {
            if (perfilEnum.name().toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(perfilEnum);
            }
        }
        return suggestions;
    }
    
    public String ExercicioBean.onEdit() {
        return null;
    }
    
    public boolean ExercicioBean.isCreateDialogVisible() {
        return createDialogVisible;
    }
    
    public void ExercicioBean.setCreateDialogVisible(boolean createDialogVisible) {
        this.createDialogVisible = createDialogVisible;
    }
    
    public String ExercicioBean.displayList() {
        createDialogVisible = false;
        findAllUsuarios();
        return "usuario";
    }
    
    public String ExercicioBean.displayCreateDialog() {
        usuario = new Usuario();
        createDialogVisible = true;
        return "usuario";
    }
    
    public String ExercicioBean.persist() {
        String message = "";
        if (usuario.getId() != null) {
            usuarioService.updateUsuario(usuario);
            message = "message_successfully_updated";
        } else {
            usuarioService.saveUsuario(usuario);
            message = "message_successfully_created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialogWidget.hide()");
        context.execute("editDialogWidget.hide()");
        
        FacesMessage facesMessage = MessageFactory.getMessage(message, "Usuario");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllUsuarios();
    }
    
    public String ExercicioBean.delete() {
        usuarioService.deleteUsuario(usuario);
        FacesMessage facesMessage = MessageFactory.getMessage("message_successfully_deleted", "Usuario");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllUsuarios();
    }
    
    public void ExercicioBean.reset() {
        usuario = null;
        createDialogVisible = false;
    }
    
    public void ExercicioBean.handleDialogClose(CloseEvent event) {
        reset();
    }
    
}
