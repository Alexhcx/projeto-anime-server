package com.clienteservidor.animeserver.animeserver.view.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.clienteservidor.animeserver.animeserver.models.EmployeeAddressModel;
import com.clienteservidor.animeserver.animeserver.models.EmployeeModel;
import com.clienteservidor.animeserver.animeserver.models.OrdersModel;
import com.clienteservidor.animeserver.animeserver.models.OrdersProductsModel;
import com.clienteservidor.animeserver.animeserver.models.PaymentModel;
import com.clienteservidor.animeserver.animeserver.models.ProductModel;
import com.clienteservidor.animeserver.animeserver.models.SupplierModel;
import com.clienteservidor.animeserver.animeserver.models.UserModel;
import com.clienteservidor.animeserver.animeserver.models.UsersAddressModel;
import com.clienteservidor.animeserver.animeserver.services.EmployeeAddressService;
import com.clienteservidor.animeserver.animeserver.services.EmployeeService;
import com.clienteservidor.animeserver.animeserver.services.OrdersProductsService;
import com.clienteservidor.animeserver.animeserver.services.OrdersService;
import com.clienteservidor.animeserver.animeserver.services.PaymentService;
import com.clienteservidor.animeserver.animeserver.services.ProductService;
import com.clienteservidor.animeserver.animeserver.services.SupplierService;
import com.clienteservidor.animeserver.animeserver.services.UsersAddressService;
import com.clienteservidor.animeserver.animeserver.services.UsersService;
import com.dansoftware.pdfdisplayer.PDFDisplayer;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/fxml/GerenciamentoView.fxml")
public class GerenciamentoViewController {

    @Autowired
    @SuppressWarnings("FieldMayBeFinal")
    private FxWeaver fxWeaver;

    @Autowired
    private UsersService usersService;

    @Autowired
    private UsersAddressService usersAddressService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeAddressService employeeAddressService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersProductsService ordersProductsService;

    @Autowired
    private PaymentService paymentService;

    @FXML
    private TableView<UserModel> tableUsuariosView;

    @FXML
    private TableView<UsersAddressModel> tabUsuariosEnderecosView;

    @FXML
    private TableView<EmployeeModel> tableFuncionariosView;

    @FXML
    private TableView<EmployeeAddressModel> tableFuncionariosEnderecosView;

    @FXML
    private TableView<ProductModel> tableProdutosView;

    @FXML
    private TableView<SupplierModel> tableFornecedoresView;

    @FXML
    private TableView<OrdersModel> tablePedidosView;

    @FXML
    private TableView<OrdersProductsModel> tablePedidosProdutosView;

    @FXML
    private TableView<PaymentModel> tablePagamentosView;

    @FXML
    private TextField nomeFuncField;

    @FXML
    private TextField emailFuncField;

    @FXML
    private TextField funcaoFuncField;

    @FXML
    private TextField salarioFuncField;

    @FXML
    private TextField cpfFuncField;

    @FXML
    private TextField telefoneFuncField;

    @FXML
    private TextField dataNascFuncField;

    @FXML
    private TextField logradouroFuncField;

    @FXML
    private TextField numeroFuncField;

    @FXML
    private TextField estadoFuncField;

    @FXML
    private TextField referenciaFuncField;

    @FXML
    private TextField bairroFuncField;

    @FXML
    private TextField complementoFuncField;

    @FXML
    private TextField cidadeFuncField;

    @FXML
    private PasswordField senhaFuncField;

    @FXML
    private TextField cepFuncField;

    @FXML
    private Button addFuncButton;

    @FXML
    private TextField nomeProdField;

    @FXML
    private TextArea descricaoProdField;

    @FXML
    private TextField precoProdField;

    @FXML
    private TextField qtdProdField;

    @FXML
    private TextField img1ProdField;

    @FXML
    private TextField img2ProdField;

    @FXML
    private ComboBox<SupplierModel> fornecedorProdutoComboBox;

    @FXML
    private Button addProdutoButton;

    @FXML
    private TextField nomeSupplierField;

    @FXML
    private TextField emailSupplierField;

    @FXML
    private TextField cnpjSupplierField;

    @FXML
    private TextField telefoneSupplierField;

    @FXML
    private TextField logradouroSupplierField;

    @FXML
    private TextField numeroSupplierField;

    @FXML
    private TextField estadoSupplierField;

    @FXML
    private TextField bairroSupplierField;

    @FXML
    private TextField complementoSupplierField;

    @FXML
    private TextField cidadeSupplierField;

    @FXML
    private TextField cepSupplierField;

    @FXML
    private Button addFornecedorButton;

    @FXML
    private ComboBox<String> relatorioComboMenu;

    @FXML
    private Button gerarRelatorioButton;

    @FXML
    private Button refreshButton;

    @Autowired
    public GerenciamentoViewController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    @SuppressWarnings("unused")
    private void initialize() {
        fxWeaver.loadView(MainController.class);
        configurarColunasTableViews();

        addFuncButton.setOnAction(event -> adicionarFuncionario());

        addFornecedorButton.setOnAction(event -> {
            adicionarFornecedor();
            atualizarProdutoComboBox();
        });

        addProdutoButton.setOnAction(event -> adicionarProduto());
        refreshButton.setOnAction(event -> preencherTableViews());

        atualizarProdutoComboBox();
        preencherTableViews();

        gerarRelatorioButton.setOnAction(event -> gerarRelatorio());

        List<String> nomesRelatorios = Arrays.asList("Relatorio-produtos-modelo", "Relatorio-usuarios-modelo","Relatorio-funcionarios-modelo");
        relatorioComboMenu.setItems(FXCollections.observableArrayList(nomesRelatorios));

        adicionarMenuContextoDeletar(tableUsuariosView, usersService::deletarUsuario);
        adicionarMenuContextoDeletar(tabUsuariosEnderecosView, usersAddressService::deletarEndereco);
        adicionarMenuContextoDeletar(tableFuncionariosView, employeeService::deletarFuncionario);
        adicionarMenuContextoDeletar(tableFuncionariosEnderecosView,
                employeeAddressService::deletarEnderecoFuncionario);
        adicionarMenuContextoDeletar(tableProdutosView, productService::deletarProduto);
        adicionarMenuContextoDeletar(tableFornecedoresView, supplierService::deletarFornecedor);
    }

    @SuppressWarnings("unchecked")
    private void configurarColunasTableViews() {

        // --- tableUsuariosView ---
        TableColumn<UserModel, Long> idUsuarioColumn = new TableColumn<>("ID");
        idUsuarioColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<UserModel, String> nomeUsuarioColumn = new TableColumn<>("Nome");
        nomeUsuarioColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nomeUsuarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));

        TableColumn<UserModel, String> emailUsuarioColumn = new TableColumn<>("Email");
        emailUsuarioColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailUsuarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));

        TableColumn<UserModel, String> cpfUsuarioColumn = new TableColumn<>("CPF");
        cpfUsuarioColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        cpfUsuarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));

        TableColumn<UserModel, String> passwordUsuarioColumn = new TableColumn<>("Senha");
        passwordUsuarioColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        passwordUsuarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));

        TableColumn<UserModel, String> sexoUsuarioColumn = new TableColumn<>("Sexo");
        sexoUsuarioColumn.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        sexoUsuarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));

        TableColumn<UserModel, String> telefoneUsuarioColumn = new TableColumn<>("Telefone");
        telefoneUsuarioColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        telefoneUsuarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));

        TableColumn<UserModel, String> dataNascimentoUsuarioColumn = new TableColumn<>("Data de Nascimento");
        dataNascimentoUsuarioColumn.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        dataNascimentoUsuarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));

        TableColumn<UserModel, LocalDateTime> criadoEmUsuarioColumn = new TableColumn<>("Criado Em");
        criadoEmUsuarioColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        TableColumn<UserModel, LocalDateTime> atualizadoEmUsuarioColumn = new TableColumn<>("Atualizado Em");
        atualizadoEmUsuarioColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        nomeUsuarioColumn.setOnEditCommit(event -> {
            UserModel pessoa = event.getRowValue();
            String novoNome = event.getNewValue();
            pessoa.setNome(novoNome);
            usersService.atualizarInfoUsuario(pessoa);
        });

        emailUsuarioColumn.setOnEditCommit(event -> {
            UserModel usuario = event.getRowValue();
            String novoEmail = event.getNewValue();
            usuario.setEmail(novoEmail);
            usersService.atualizarInfoUsuario(usuario);
        });

        cpfUsuarioColumn.setOnEditCommit(event -> {
            UserModel usuario = event.getRowValue();
            String novoCpf = event.getNewValue();
            usuario.setCpf(novoCpf);
            usersService.atualizarInfoUsuario(usuario);
        });

        passwordUsuarioColumn.setOnEditCommit(event -> {
            UserModel usuario = event.getRowValue();
            String novaSenha = event.getNewValue();
            usuario.setPassword(novaSenha);
            usersService.atualizarInfoUsuario(usuario);
        });

        sexoUsuarioColumn.setOnEditCommit(event -> {
            UserModel usuario = event.getRowValue();
            String novoSexo = event.getNewValue();
            usuario.setSexo(novoSexo);
            usersService.atualizarInfoUsuario(usuario);
        });

        telefoneUsuarioColumn.setOnEditCommit(event -> {
            UserModel usuario = event.getRowValue();
            String novoTelefone = event.getNewValue();
            usuario.setTelefone(novoTelefone);
            usersService.atualizarInfoUsuario(usuario);
        });

        dataNascimentoUsuarioColumn.setOnEditCommit(event -> {
            UserModel usuario = event.getRowValue();
            String novaDataNascimento = event.getNewValue();
            usuario.setDataNascimento(novaDataNascimento);
            usersService.atualizarInfoUsuario(usuario);
        });

        tableUsuariosView.getColumns().addAll(
                idUsuarioColumn,
                nomeUsuarioColumn,
                emailUsuarioColumn,
                cpfUsuarioColumn,
                passwordUsuarioColumn,
                sexoUsuarioColumn,
                telefoneUsuarioColumn,
                dataNascimentoUsuarioColumn,
                criadoEmUsuarioColumn,
                atualizadoEmUsuarioColumn);

        // --- tabUsuariosEnderecosView ---
        TableColumn<UsersAddressModel, Long> idEnderecoColumn = new TableColumn<>("ID");
        idEnderecoColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<UsersAddressModel, String> idUsuarioEnderecoColumn = new TableColumn<>("ID do Usuário");
        idUsuarioEnderecoColumn.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getUser().getId().toString()));

        TableColumn<UsersAddressModel, String> logradouroColumn = new TableColumn<>("Logradouro");
        logradouroColumn.setCellValueFactory(new PropertyValueFactory<>("logradouro"));

        TableColumn<UsersAddressModel, String> numeroColumn = new TableColumn<>("Número");
        numeroColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));

        TableColumn<UsersAddressModel, String> bairroColumn = new TableColumn<>("Bairro");
        bairroColumn.setCellValueFactory(new PropertyValueFactory<>("bairro"));

        TableColumn<UsersAddressModel, String> cidadeColumn = new TableColumn<>("Cidade");
        cidadeColumn.setCellValueFactory(new PropertyValueFactory<>("cidade"));

        TableColumn<UsersAddressModel, String> estadoColumn = new TableColumn<>("Estado");
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));

        TableColumn<UsersAddressModel, String> complementoColumn = new TableColumn<>("Complemento");
        complementoColumn.setCellValueFactory(new PropertyValueFactory<>("complemento"));

        TableColumn<UsersAddressModel, String> referenciaColumn = new TableColumn<>("Referência");
        referenciaColumn.setCellValueFactory(new PropertyValueFactory<>("referencia"));

        TableColumn<UsersAddressModel, LocalDateTime> criadoEmEnderecoColumn = new TableColumn<>("Criado Em");
        criadoEmEnderecoColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        TableColumn<UsersAddressModel, LocalDateTime> atualizadoEmEnderecoColumn = new TableColumn<>("Atualizado Em");
        atualizadoEmEnderecoColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        idUsuarioEnderecoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        idUsuarioEnderecoColumn.setOnEditCommit(event -> {
            UsersAddressModel endereco = event.getRowValue();
            @SuppressWarnings("UnnecessaryTemporaryOnConversionFromString")
            Long novoIdUsuario = Long.parseLong(event.getNewValue()); 
            UserModel usuario = usersService.buscarUsuarioPorId(novoIdUsuario); 
            endereco.setUser(usuario); 
            usersAddressService.atualizarEndereco(endereco);
        });

        logradouroColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        logradouroColumn.setOnEditCommit(event -> {
            UsersAddressModel endereco = event.getRowValue();
            String novoLogradouro = event.getNewValue();
            endereco.setLogradouro(novoLogradouro);
            usersAddressService.atualizarEndereco(endereco);
        });

        numeroColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        numeroColumn.setOnEditCommit(event -> {
            UsersAddressModel endereco = event.getRowValue();
            String novoNumero = event.getNewValue();
            endereco.setNumero(novoNumero);
            usersAddressService.atualizarEndereco(endereco);
        });

        bairroColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        bairroColumn.setOnEditCommit(event -> {
            UsersAddressModel endereco = event.getRowValue();
            String novoBairro = event.getNewValue();
            endereco.setBairro(novoBairro);
            usersAddressService.atualizarEndereco(endereco);
        });

        cidadeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        cidadeColumn.setOnEditCommit(event -> {
            UsersAddressModel endereco = event.getRowValue();
            String novaCidade = event.getNewValue();
            endereco.setCidade(novaCidade);
            usersAddressService.atualizarEndereco(endereco);
        });

        estadoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        estadoColumn.setOnEditCommit(event -> {
            UsersAddressModel endereco = event.getRowValue();
            String novoEstado = event.getNewValue();
            endereco.setEstado(novoEstado);
            usersAddressService.atualizarEndereco(endereco);
        });

        complementoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        complementoColumn.setOnEditCommit(event -> {
            UsersAddressModel endereco = event.getRowValue();
            String novoComplemento = event.getNewValue();
            endereco.setComplemento(novoComplemento);
            usersAddressService.atualizarEndereco(endereco);
        });

        referenciaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        referenciaColumn.setOnEditCommit(event -> {
            UsersAddressModel endereco = event.getRowValue();
            String novaReferencia = event.getNewValue();
            endereco.setReferencia(novaReferencia);
            usersAddressService.atualizarEndereco(endereco);
        });

        tabUsuariosEnderecosView.getColumns().addAll(
                idEnderecoColumn,
                idUsuarioEnderecoColumn,
                logradouroColumn,
                numeroColumn,
                bairroColumn,
                cidadeColumn,
                estadoColumn,
                complementoColumn,
                referenciaColumn,
                criadoEmEnderecoColumn,
                atualizadoEmEnderecoColumn);

        // --- tableFuncionariosView ---
        TableColumn<EmployeeModel, Long> idFuncionarioColumn = new TableColumn<>("ID");
        idFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<EmployeeModel, String> nomeFuncionarioColumn = new TableColumn<>("Nome");
        nomeFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<EmployeeModel, String> emailFuncionarioColumn = new TableColumn<>("Email");
        emailFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<EmployeeModel, String> cpfFuncionarioColumn = new TableColumn<>("CPF");
        cpfFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        TableColumn<EmployeeModel, String> senhaFuncionarioColumn = new TableColumn<>("Senha");
        senhaFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<EmployeeModel, String> funcaoFuncionarioColumn = new TableColumn<>("Função");
        funcaoFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("funcao"));

        TableColumn<EmployeeModel, String> salarioFuncionarioColumn = new TableColumn<>("Salário");
        salarioFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("salario"));

        TableColumn<EmployeeModel, String> telefoneFuncionarioColumn = new TableColumn<>("Telefone");
        telefoneFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        TableColumn<EmployeeModel, String> dataNascimentoFuncionarioColumn = new TableColumn<>("Data de Nascimento");
        dataNascimentoFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));

        TableColumn<EmployeeModel, LocalDateTime> criadoEmFuncionarioColumn = new TableColumn<>("Criado Em");
        criadoEmFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        TableColumn<EmployeeModel, LocalDateTime> atualizadoEmFuncionarioColumn = new TableColumn<>("Atualizado Em");
        atualizadoEmFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        nomeFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        nomeFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeModel funcionario = event.getRowValue();
            String novoNome = event.getNewValue();
            funcionario.setNome(novoNome);
            employeeService.atualizarInformacoesFuncionario(funcionario);
        });

        emailFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        emailFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeModel funcionario = event.getRowValue();
            String novoEmail = event.getNewValue();
            funcionario.setEmail(novoEmail);
            employeeService.atualizarInformacoesFuncionario(funcionario);
        });

        cpfFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        cpfFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeModel funcionario = event.getRowValue();
            String novoCpf = event.getNewValue();
            funcionario.setCpf(novoCpf);
            employeeService.atualizarInformacoesFuncionario(funcionario);
        });

        senhaFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        senhaFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeModel funcionario = event.getRowValue();
            String novaSenha = event.getNewValue();
            funcionario.setPassword(novaSenha);
            employeeService.atualizarInformacoesFuncionario(funcionario);
        });

        funcaoFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        funcaoFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeModel funcionario = event.getRowValue();
            String novaFuncao = event.getNewValue();
            funcionario.setFuncao(novaFuncao);
            employeeService.atualizarInformacoesFuncionario(funcionario);
        });

        salarioFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        salarioFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeModel funcionario = event.getRowValue();
            String novoSalario = event.getNewValue();
            funcionario.setSalario(novoSalario);
            employeeService.atualizarInformacoesFuncionario(funcionario);
        });

        telefoneFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        telefoneFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeModel funcionario = event.getRowValue();
            String novoTelefone = event.getNewValue();
            funcionario.setTelefone(novoTelefone);
            employeeService.atualizarInformacoesFuncionario(funcionario);
        });

        dataNascimentoFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        dataNascimentoFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeModel funcionario = event.getRowValue();
            String novaDataNascimento = event.getNewValue();
            funcionario.setDataNascimento(novaDataNascimento);
            employeeService.atualizarInformacoesFuncionario(funcionario);
        });

        tableFuncionariosView.getColumns().addAll(
                idFuncionarioColumn, nomeFuncionarioColumn, emailFuncionarioColumn, cpfFuncionarioColumn,
                senhaFuncionarioColumn, funcaoFuncionarioColumn, salarioFuncionarioColumn, telefoneFuncionarioColumn,
                dataNascimentoFuncionarioColumn, criadoEmFuncionarioColumn, atualizadoEmFuncionarioColumn);

        // --- tableFuncionariosEnderecosView ---
        TableColumn<EmployeeAddressModel, Long> idFuncionarioEnderecoColumn = new TableColumn<>("ID");
        idFuncionarioEnderecoColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<EmployeeAddressModel, String> idFuncionarioEnderecoColumn2 = new TableColumn<>("ID do Funcionário");
        idFuncionarioEnderecoColumn2.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getEmployee().getId().toString()));

        TableColumn<EmployeeAddressModel, String> logradouroFuncionarioColumn = new TableColumn<>("Logradouro");
        logradouroFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("logradouro"));

        TableColumn<EmployeeAddressModel, String> numeroFuncionarioColumn = new TableColumn<>("Número");
        numeroFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));

        TableColumn<EmployeeAddressModel, String> bairroFuncionarioColumn = new TableColumn<>("Bairro");
        bairroFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("bairro"));

        TableColumn<EmployeeAddressModel, String> cidadeFuncionarioColumn = new TableColumn<>("Cidade");
        cidadeFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("cidade"));

        TableColumn<EmployeeAddressModel, String> estadoFuncionarioColumn = new TableColumn<>("Estado");
        estadoFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));

        TableColumn<EmployeeAddressModel, String> complementoFuncionarioColumn = new TableColumn<>("Complemento");
        complementoFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("complemento"));

        TableColumn<EmployeeAddressModel, String> referenciaFuncionarioColumn = new TableColumn<>("Referência");
        referenciaFuncionarioColumn.setCellValueFactory(new PropertyValueFactory<>("referencia"));

        TableColumn<EmployeeAddressModel, LocalDateTime> criadoEmFuncionarioEnderecoColumn = new TableColumn<>(
                "Criado Em");
        criadoEmFuncionarioEnderecoColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        TableColumn<EmployeeAddressModel, LocalDateTime> atualizadoEmFuncionarioEnderecoColumn = new TableColumn<>(
                "Atualizado Em");
        atualizadoEmFuncionarioEnderecoColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        idFuncionarioEnderecoColumn2.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        idFuncionarioEnderecoColumn2.setOnEditCommit(event -> {
            EmployeeAddressModel endereco = event.getRowValue();
            @SuppressWarnings("UnnecessaryTemporaryOnConversionFromString")
            Long novoIdFuncionario = Long.parseLong(event.getNewValue());
            EmployeeModel funcionario = employeeService.buscarFuncionarioPorId(novoIdFuncionario);
            endereco.setEmployee(funcionario);
            employeeAddressService.atualizarEnderecoFuncionario(endereco);
        });

        logradouroFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        logradouroFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeAddressModel endereco = event.getRowValue();
            String novoLogradouro = event.getNewValue();
            endereco.setLogradouro(novoLogradouro);
            employeeAddressService.atualizarEnderecoFuncionario(endereco);
        });

        numeroFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        numeroFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeAddressModel endereco = event.getRowValue();
            String novoNumero = event.getNewValue();
            endereco.setNumero(novoNumero);
            employeeAddressService.atualizarEnderecoFuncionario(endereco);
        });

        bairroFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        bairroFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeAddressModel endereco = event.getRowValue();
            String novoBairro = event.getNewValue();
            endereco.setBairro(novoBairro);
            employeeAddressService.atualizarEnderecoFuncionario(endereco);
        });

        cidadeFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        cidadeFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeAddressModel endereco = event.getRowValue();
            String novaCidade = event.getNewValue();
            endereco.setCidade(novaCidade);
            employeeAddressService.atualizarEnderecoFuncionario(endereco);
        });

        estadoFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        estadoFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeAddressModel endereco = event.getRowValue();
            String novoEstado = event.getNewValue();
            endereco.setEstado(novoEstado);
            employeeAddressService.atualizarEnderecoFuncionario(endereco);
        });

        complementoFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        complementoFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeAddressModel endereco = event.getRowValue();
            String novoComplemento = event.getNewValue();
            endereco.setComplemento(novoComplemento);
            employeeAddressService.atualizarEnderecoFuncionario(endereco);
        });

        referenciaFuncionarioColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        referenciaFuncionarioColumn.setOnEditCommit(event -> {
            EmployeeAddressModel endereco = event.getRowValue();
            String novaReferencia = event.getNewValue();
            endereco.setReferencia(novaReferencia);
            employeeAddressService.atualizarEnderecoFuncionario(endereco);
        });

        tableFuncionariosEnderecosView.getColumns().addAll(
                idFuncionarioEnderecoColumn, idFuncionarioEnderecoColumn2, logradouroFuncionarioColumn,
                numeroFuncionarioColumn, bairroFuncionarioColumn, cidadeFuncionarioColumn, estadoFuncionarioColumn,
                complementoFuncionarioColumn, referenciaFuncionarioColumn, criadoEmFuncionarioEnderecoColumn,
                atualizadoEmFuncionarioEnderecoColumn);

        // --- tableProdutosView ---
        TableColumn<ProductModel, Long> idProdutoColumn = new TableColumn<>("ID");
        idProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<ProductModel, String> supplierIdColumn = new TableColumn<>("ID do Fornecedor");
        supplierIdColumn.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getSupplier().getId().toString()));
        supplierIdColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        supplierIdColumn.setOnEditCommit(event -> {
            ProductModel produto = event.getRowValue();
            Long novoIdFornecedor = Long.valueOf(event.getNewValue());
            SupplierModel fornecedor = supplierService.buscarFornecedorPorId(novoIdFornecedor);
            produto.setSupplier(fornecedor);
            productService.atualizarProduto(produto);
        });

        TableColumn<ProductModel, String> nomeProdutoColumn = new TableColumn<>("Nome");
        nomeProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nomeProdutoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        nomeProdutoColumn.setOnEditCommit(event -> {
            ProductModel produto = event.getRowValue();
            String novoNome = event.getNewValue();
            produto.setNome(novoNome);
            productService.atualizarProduto(produto);
        });

        TableColumn<ProductModel, String> descricaoProdutoColumn = new TableColumn<>("Descrição");
        descricaoProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        descricaoProdutoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        descricaoProdutoColumn.setOnEditCommit(event -> {
            ProductModel produto = event.getRowValue();
            String novaDescricao = event.getNewValue();
            produto.setDescricao(novaDescricao);
            productService.atualizarProduto(produto);
        });

        TableColumn<ProductModel, String> precoProdutoColumn = new TableColumn<>("Preço");
        precoProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));
        precoProdutoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        precoProdutoColumn.setOnEditCommit(event -> {
            ProductModel produto = event.getRowValue();
            String novoPreco = event.getNewValue();
            produto.setPreco(novoPreco);
            productService.atualizarProduto(produto);
        });

        TableColumn<ProductModel, String> quantidadeEmEstoqueProdutoColumn = new TableColumn<>("Quantidade em Estoque");
        quantidadeEmEstoqueProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("qtdEstoque"));
        quantidadeEmEstoqueProdutoColumn
                .setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        quantidadeEmEstoqueProdutoColumn.setOnEditCommit(event -> {
            ProductModel produto = event.getRowValue();
            String novaQuantidadeEmEstoque = event.getNewValue();
            produto.setQtdEstoque(novaQuantidadeEmEstoque);
            productService.atualizarProduto(produto);
        });

        TableColumn<ProductModel, String> imagensColumn = new TableColumn<>("Imagens");
        imagensColumn.setCellValueFactory(cellData -> {
            String[] imagens = cellData.getValue().getImagens();
            return new SimpleStringProperty(String.join(", ", imagens));
        });

        imagensColumn.setPrefWidth(100);

        TableColumn<ProductModel, LocalDateTime> criadoEmProdutoColumn = new TableColumn<>("Criado Em");
        criadoEmProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        TableColumn<ProductModel, LocalDateTime> atualizadoEmProdutoColumn = new TableColumn<>("Atualizado Em");
        atualizadoEmProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        tableProdutosView.getColumns().addAll(
                idProdutoColumn,
                supplierIdColumn,
                nomeProdutoColumn,
                descricaoProdutoColumn,
                precoProdutoColumn,
                imagensColumn,
                quantidadeEmEstoqueProdutoColumn,
                criadoEmProdutoColumn,
                atualizadoEmProdutoColumn);

        // --- tableFornecedoresView ---
        TableColumn<SupplierModel, Long> idFornecedorColumn = new TableColumn<>("ID");
        idFornecedorColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<SupplierModel, String> nomeFornecedorColumn = new TableColumn<>("Nome");
        nomeFornecedorColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        nomeFornecedorColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        nomeFornecedorColumn.setOnEditCommit(event -> {
            SupplierModel fornecedor = event.getRowValue();
            String novoNome = event.getNewValue();
            fornecedor.setNome(novoNome);
            supplierService.atualizarFornecedor(fornecedor);
        });

        TableColumn<SupplierModel, String> cnpjFornecedorColumn = new TableColumn<>("CNPJ");
        cnpjFornecedorColumn.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        cnpjFornecedorColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        cnpjFornecedorColumn.setOnEditCommit(event -> {
            SupplierModel fornecedor = event.getRowValue();
            String novoCnpj = event.getNewValue();
            fornecedor.setCnpj(novoCnpj);
            supplierService.atualizarFornecedor(fornecedor);
        });

        TableColumn<SupplierModel, String> emailFornecedorColumn = new TableColumn<>("Email");
        emailFornecedorColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailFornecedorColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        emailFornecedorColumn.setOnEditCommit(event -> {
            SupplierModel fornecedor = event.getRowValue();
            String novoEmail = event.getNewValue();
            fornecedor.setEmail(novoEmail);
            supplierService.atualizarFornecedor(fornecedor);
        });

        TableColumn<SupplierModel, String> telefoneFornecedorColumn = new TableColumn<>("Telefone");
        telefoneFornecedorColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        telefoneFornecedorColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        telefoneFornecedorColumn.setOnEditCommit(event -> {
            SupplierModel fornecedor = event.getRowValue();
            String novoTelefone = event.getNewValue();
            fornecedor.setTelefone(novoTelefone);
            supplierService.atualizarFornecedor(fornecedor);
        });

        TableColumn<SupplierModel, String> cepFornecedorColumn = new TableColumn<>("CEP");
        cepFornecedorColumn.setCellValueFactory(new PropertyValueFactory<>("cep"));
        cepFornecedorColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        cepFornecedorColumn.setOnEditCommit(event -> {
            SupplierModel fornecedor = event.getRowValue();
            String novoCep = event.getNewValue();
            fornecedor.setCep(novoCep);
            supplierService.atualizarFornecedor(fornecedor);
        });

        TableColumn<SupplierModel, String> logradouroFornecedorColumn = new TableColumn<>("Logradouro");
        logradouroFornecedorColumn.setCellValueFactory(new PropertyValueFactory<>("logradouro"));
        logradouroFornecedorColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        logradouroFornecedorColumn.setOnEditCommit(event -> {
            SupplierModel fornecedor = event.getRowValue();
            String novoLogradouro = event.getNewValue();
            fornecedor.setLogradouro(novoLogradouro);
            supplierService.atualizarFornecedor(fornecedor);
        });

        TableColumn<SupplierModel, String> bairroFornecedorColumn = new TableColumn<>("Bairro");
        bairroFornecedorColumn.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        bairroFornecedorColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        bairroFornecedorColumn.setOnEditCommit(event -> {
            SupplierModel fornecedor = event.getRowValue();
            String novoBairro = event.getNewValue();
            fornecedor.setBairro(novoBairro);
            supplierService.atualizarFornecedor(fornecedor);
        });

        TableColumn<SupplierModel, String> cidadeFornecedorColumn = new TableColumn<>("Cidade");
        cidadeFornecedorColumn.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        cidadeFornecedorColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        cidadeFornecedorColumn.setOnEditCommit(event -> {
            SupplierModel fornecedor = event.getRowValue();
            String novaCidade = event.getNewValue();
            fornecedor.setCidade(novaCidade);
            supplierService.atualizarFornecedor(fornecedor);
        });

        TableColumn<SupplierModel, String> estadoFornecedorColumn = new TableColumn<>("Estado");
        estadoFornecedorColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));
        estadoFornecedorColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        estadoFornecedorColumn.setOnEditCommit(event -> {
            SupplierModel fornecedor = event.getRowValue();
            String novoEstado = event.getNewValue();
            fornecedor.setEstado(novoEstado);
            supplierService.atualizarFornecedor(fornecedor);
        });

        TableColumn<SupplierModel, String> numeroFornecedorColumn = new TableColumn<>("Número");
        numeroFornecedorColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
        numeroFornecedorColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        numeroFornecedorColumn.setOnEditCommit(event -> {
            SupplierModel fornecedor = event.getRowValue();
            String novoNumero = event.getNewValue();
            fornecedor.setNumero(novoNumero);
            supplierService.atualizarFornecedor(fornecedor);
        });

        TableColumn<SupplierModel, String> complementoFornecedorColumn = new TableColumn<>("Complemento");
        complementoFornecedorColumn.setCellValueFactory(new PropertyValueFactory<>("complemento"));
        complementoFornecedorColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        complementoFornecedorColumn.setOnEditCommit(event -> {
            SupplierModel fornecedor = event.getRowValue();
            String novoComplemento = event.getNewValue();
            fornecedor.setComplemento(novoComplemento);
            supplierService.atualizarFornecedor(fornecedor);
        });

        TableColumn<SupplierModel, LocalDateTime> criadoEmFornecedorColumn = new TableColumn<>("Criado Em");
        criadoEmFornecedorColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        TableColumn<SupplierModel, LocalDateTime> atualizadoEmFornecedorColumn = new TableColumn<>("Atualizado Em");
        atualizadoEmFornecedorColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        tableFornecedoresView.getColumns().addAll(
                idFornecedorColumn,
                nomeFornecedorColumn,
                cnpjFornecedorColumn,
                emailFornecedorColumn,
                telefoneFornecedorColumn,
                cepFornecedorColumn,
                logradouroFornecedorColumn,
                bairroFornecedorColumn,
                cidadeFornecedorColumn,
                estadoFornecedorColumn,
                numeroFornecedorColumn,
                complementoFornecedorColumn,
                criadoEmFornecedorColumn,
                atualizadoEmFornecedorColumn);

        // --- tablePedidosView ---
        TableColumn<OrdersModel, Long> idPedidoColumn = new TableColumn<>("ID");
        idPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<OrdersModel, String> statusPedidoColumn = new TableColumn<>("Status");
        statusPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusPedidoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        statusPedidoColumn.setOnEditCommit(event -> {
            OrdersModel pedido = event.getRowValue();
            String novoStatus = event.getNewValue();
            pedido.setStatus(novoStatus);
            ordersService.atualizarPedido(pedido);
        });

        TableColumn<OrdersModel, String> valorTotalPedidoColumn = new TableColumn<>("Valor Total");
        valorTotalPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
        valorTotalPedidoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        valorTotalPedidoColumn.setOnEditCommit(event -> {
            OrdersModel pedido = event.getRowValue();
            String novoValorTotal = event.getNewValue();
            pedido.setValorTotal(novoValorTotal);
            ordersService.atualizarPedido(pedido);
        });

        TableColumn<OrdersModel, String> metodoEnvioPedidoColumn = new TableColumn<>("Método de Envio");
        metodoEnvioPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("metodoEnvio"));
        metodoEnvioPedidoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        metodoEnvioPedidoColumn.setOnEditCommit(event -> {
            OrdersModel pedido = event.getRowValue();
            String novoMetodoEnvio = event.getNewValue();
            pedido.setMetodoEnvio(novoMetodoEnvio);
            ordersService.atualizarPedido(pedido);
        });

        TableColumn<OrdersModel, String> custoEnvioPedidoColumn = new TableColumn<>("Custo de Envio");
        custoEnvioPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("custoEnvio"));
        custoEnvioPedidoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        custoEnvioPedidoColumn.setOnEditCommit(event -> {
            OrdersModel pedido = event.getRowValue();
            String novoCustoEnvio = event.getNewValue();
            pedido.setCustoEnvio(novoCustoEnvio);
            ordersService.atualizarPedido(pedido);
        });

        TableColumn<OrdersModel, String> numeroRastreamentoPedidoColumn = new TableColumn<>("Número de Rastreamento");
        numeroRastreamentoPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("numeroRastreamento"));
        numeroRastreamentoPedidoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        numeroRastreamentoPedidoColumn.setOnEditCommit(event -> {
            OrdersModel pedido = event.getRowValue();
            String novoNumeroRastreamento = event.getNewValue();
            pedido.setNumeroRastreamento(novoNumeroRastreamento);
            ordersService.atualizarPedido(pedido);
        });

        TableColumn<OrdersModel, LocalDateTime> criadoEmPedidoColumn = new TableColumn<>("Criado Em");
        criadoEmPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        TableColumn<OrdersModel, LocalDateTime> atualizadoEmPedidoColumn = new TableColumn<>("Atualizado Em");
        atualizadoEmPedidoColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        tablePedidosView.getColumns().addAll(
                idPedidoColumn,
                statusPedidoColumn,
                valorTotalPedidoColumn,
                metodoEnvioPedidoColumn,
                custoEnvioPedidoColumn,
                numeroRastreamentoPedidoColumn,
                criadoEmPedidoColumn,
                atualizadoEmPedidoColumn);

        // --- tablePedidosProdutosView ---
        TableColumn<OrdersProductsModel, Long> idPedidoProdutoColumn = new TableColumn<>("ID");
        idPedidoProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<OrdersProductsModel, String> idPedidoProColumn = new TableColumn<>("ID do Pedido");
        idPedidoProColumn.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getOrder().getId().toString()));

        idPedidoProColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        idPedidoProColumn.setOnEditCommit(event -> {
            OrdersProductsModel ordersProducts = event.getRowValue();
            Long novoIdPedido = Long.valueOf(event.getNewValue());
            OrdersModel pedido = ordersService.buscarPedidoPorId(novoIdPedido);
            ordersProducts.setOrder(pedido);
            ordersProductsService.atualizarProdutoNoPedido(
                    ordersProducts.getOrder().getId(),
                    ordersProducts.getProduct().getId(),
                    ordersProducts.getQtdProduto());
        });

        TableColumn<OrdersProductsModel, String> idPeProdutoColumn = new TableColumn<>("ID do Produto");
        idPeProdutoColumn.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getProduct().getId().toString()));
        idPeProdutoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        idPeProdutoColumn.setOnEditCommit(event -> {
            OrdersProductsModel ordersProducts = event.getRowValue();
            Long novoIdProduto = Long.valueOf(event.getNewValue());
            ProductModel produto = productService.buscarProdutoPorId(novoIdProduto);
            ordersProducts.setProduct(produto);
            ordersProductsService.atualizarProdutoNoPedido(
                    ordersProducts.getOrder().getId(),
                    ordersProducts.getProduct().getId(),
                    ordersProducts.getQtdProduto());
        });

        TableColumn<OrdersProductsModel, Long> qtdProdutoColumn = new TableColumn<>("Quantidade do Produto");
        qtdProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("qtdProduto"));
        qtdProdutoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        qtdProdutoColumn.setOnEditCommit(event -> {
            OrdersProductsModel ordersProducts = event.getRowValue();
            Long novaQtd = event.getNewValue();
            ordersProductsService.atualizarProdutoNoPedido(
                    ordersProducts.getOrder().getId(),
                    ordersProducts.getProduct().getId(),
                    novaQtd);
        });

        tablePedidosProdutosView.getColumns().addAll(
                idPedidoProdutoColumn,
                idPedidoProColumn,
                idPeProdutoColumn,
                qtdProdutoColumn);

        // --- tablePagamentosView ---
        TableColumn<PaymentModel, Long> idPagamentoColumn = new TableColumn<>("ID");
        idPagamentoColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<PaymentModel, String> metodoPagamentoColumn = new TableColumn<>("Método de Pagamento");
        metodoPagamentoColumn.setCellValueFactory(new PropertyValueFactory<>("metodoPagamento"));
        metodoPagamentoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        metodoPagamentoColumn.setOnEditCommit(event -> {
            PaymentModel pagamento = event.getRowValue();
            String novoMetodoPagamento = event.getNewValue();
            pagamento.setMetodoPagamento(novoMetodoPagamento);
            paymentService.atualizarPagamento(pagamento);
        });

        TableColumn<PaymentModel, String> statusPagamentoColumn = new TableColumn<>("Status do Pagamento");
        statusPagamentoColumn.setCellValueFactory(new PropertyValueFactory<>("statusPagamento"));
        statusPagamentoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        statusPagamentoColumn.setOnEditCommit(event -> {
            PaymentModel pagamento = event.getRowValue();
            String novoStatusPagamento = event.getNewValue();
            pagamento.setStatusPagamento(novoStatusPagamento);
            paymentService.atualizarPagamento(pagamento);
        });

        TableColumn<PaymentModel, LocalDateTime> criadoEmPagamentoColumn = new TableColumn<>("Criado Em");
        criadoEmPagamentoColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

        TableColumn<PaymentModel, LocalDateTime> atualizadoEmPagamentoColumn = new TableColumn<>("Atualizado Em");
        atualizadoEmPagamentoColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        tablePagamentosView.getColumns().addAll(
                idPagamentoColumn,
                metodoPagamentoColumn,
                statusPagamentoColumn,
                criadoEmPagamentoColumn,
                atualizadoEmPagamentoColumn);

    }

    private void preencherTableViews() {

        // Usuários
        List<UserModel> usuarios = usersService.mostrarTodosOsUsuarios();
        ObservableList<UserModel> usuariosData = FXCollections.observableArrayList(usuarios);
        tableUsuariosView.setItems(usuariosData);

        // Endereços dos usuários
        List<UsersAddressModel> enderecos = new ArrayList<>();
        for (UserModel usuario : usuarios) {
            enderecos.addAll(usersAddressService.listarEnderecosDoUsuario(usuario.getId()));
        }
        ObservableList<UsersAddressModel> enderecosData = FXCollections.observableArrayList(enderecos);
        tabUsuariosEnderecosView.setItems(enderecosData);

        // Funcionários
        List<EmployeeModel> funcionarios = employeeService.mostrarTodosOsFuncionarios();
        ObservableList<EmployeeModel> funcionariosData = FXCollections.observableArrayList(funcionarios);
        tableFuncionariosView.setItems(funcionariosData);

        // Endereços dos funcionários
        List<EmployeeAddressModel> enderecosFuncionarios = new ArrayList<>();
        for (EmployeeModel funcionario : funcionarios) {
            enderecosFuncionarios.addAll(employeeAddressService.listarEnderecosDoFuncionario(funcionario.getId()));
        }
        ObservableList<EmployeeAddressModel> enderecosFuncionariosData = FXCollections
                .observableArrayList(enderecosFuncionarios);
        tableFuncionariosEnderecosView.setItems(enderecosFuncionariosData);

        // Produtos
        List<ProductModel> produtos = productService.mostrarTodosOsProdutos();
        ObservableList<ProductModel> produtosData = FXCollections.observableArrayList(produtos);
        tableProdutosView.setItems(produtosData);

        // Fornecedores
        List<SupplierModel> fornecedores = supplierService.mostrarTodosOsFornecedores();
        ObservableList<SupplierModel> fornecedoresData = FXCollections.observableArrayList(fornecedores);
        tableFornecedoresView.setItems(fornecedoresData);

        // Pedidos
        List<OrdersModel> pedidos = ordersService.listarTodosOsPedidos();
        ObservableList<OrdersModel> pedidosData = FXCollections.observableArrayList(pedidos);
        tablePedidosView.setItems(pedidosData);

        // Dados dos pedidos e produtos
        List<OrdersProductsModel> pedidosProdutos = ordersProductsService.listarTodosOsPedidosProdutos();
        ObservableList<OrdersProductsModel> pedidosProdutosData = FXCollections.observableArrayList(pedidosProdutos);
        tablePedidosProdutosView.setItems(pedidosProdutosData);

        // Pagamentos
        List<PaymentModel> pagamentos = paymentService.listarTodosOsPagamentos();
        ObservableList<PaymentModel> pagamentosData = FXCollections.observableArrayList(pagamentos);
        tablePagamentosView.setItems(pagamentosData);

    }

    @FXML
    private void adicionarFuncionario() {
        try {
            String nome = nomeFuncField.getText();
            String email = emailFuncField.getText();
            String funcao = funcaoFuncField.getText();
            String salario = salarioFuncField.getText();
            String cpf = cpfFuncField.getText();
            String telefone = telefoneFuncField.getText();
            String dataNascimento = dataNascFuncField.getText();
            String logradouro = logradouroFuncField.getText();
            String numero = numeroFuncField.getText();
            String estado = estadoFuncField.getText();
            String referencia = referenciaFuncField.getText();
            String bairro = bairroFuncField.getText();
            String complemento = complementoFuncField.getText();
            String cidade = cidadeFuncField.getText();
            String senha = senhaFuncField.getText();
            String cep = cepFuncField.getText();

            EmployeeModel novoFuncionario = new EmployeeModel();
            novoFuncionario.setNome(nome);
            novoFuncionario.setEmail(email);
            novoFuncionario.setFuncao(funcao);
            novoFuncionario.setSalario(salario); 
            novoFuncionario.setCpf(cpf);
            novoFuncionario.setTelefone(telefone);
            novoFuncionario.setDataNascimento(dataNascimento); 
            novoFuncionario.setPassword(senha);

            EmployeeModel funcionarioSalvo = employeeService.cadastrarFuncionario(novoFuncionario);

            EmployeeAddressModel enderecoFuncionario = new EmployeeAddressModel();
            enderecoFuncionario.setEmployee(funcionarioSalvo); // Associa o endereço ao funcionário
            enderecoFuncionario.setLogradouro(logradouro);
            enderecoFuncionario.setNumero(numero);
            enderecoFuncionario.setEstado(estado);
            enderecoFuncionario.setReferencia(referencia);
            enderecoFuncionario.setBairro(bairro);
            enderecoFuncionario.setComplemento(complemento);
            enderecoFuncionario.setCidade(cidade);
            enderecoFuncionario.setCep(cep);

            employeeAddressService.cadastrarEnderecoFuncionario(enderecoFuncionario);

            limparCamposFuncionario();

            atualizarTabelaFuncionarios();
            atualizarTabelaEnderecosFuncionarios();

        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao adicionar funcionário: " + e.getMessage());
        }
    }

    private void limparCamposFuncionario() {
        nomeFuncField.clear();
        emailFuncField.clear();
        funcaoFuncField.clear();
        salarioFuncField.clear();
        cpfFuncField.clear();
        telefoneFuncField.clear();
        dataNascFuncField.clear();
        logradouroFuncField.clear();
        numeroFuncField.clear();
        estadoFuncField.clear();
        referenciaFuncField.clear();
        bairroFuncField.clear();
        complementoFuncField.clear();
        cidadeFuncField.clear();
        senhaFuncField.clear();
        cepFuncField.clear();
    }

    private void atualizarTabelaFuncionarios() {
        List<EmployeeModel> funcionarios = employeeService.mostrarTodosOsFuncionarios();
        ObservableList<EmployeeModel> funcionariosData = FXCollections.observableArrayList(funcionarios);
        tableFuncionariosView.setItems(funcionariosData);
    }

    private void atualizarTabelaEnderecosFuncionarios() {
        List<EmployeeAddressModel> enderecosFuncionarios = new ArrayList<>();

        List<EmployeeModel> funcionarios = employeeService.mostrarTodosOsFuncionarios();

        for (EmployeeModel funcionario : funcionarios) {
            enderecosFuncionarios.addAll(employeeAddressService.listarEnderecosDoFuncionario(funcionario.getId()));
        }

        ObservableList<EmployeeAddressModel> enderecosFuncionariosData = FXCollections
                .observableArrayList(enderecosFuncionarios);
        tableFuncionariosEnderecosView.setItems(enderecosFuncionariosData);
    }

    @FXML
    private void adicionarFornecedor() {
        try {
            String nome = nomeSupplierField.getText();
            String email = emailSupplierField.getText();
            String cnpj = cnpjSupplierField.getText();
            String telefone = telefoneSupplierField.getText();
            String logradouro = logradouroSupplierField.getText();
            String numero = numeroSupplierField.getText();
            String estado = estadoSupplierField.getText();
            String bairro = bairroSupplierField.getText();
            String complemento = complementoSupplierField.getText();
            String cidade = cidadeSupplierField.getText();
            String cep = cepSupplierField.getText();

            SupplierModel novoFornecedor = new SupplierModel();
            novoFornecedor.setNome(nome);
            novoFornecedor.setEmail(email);
            novoFornecedor.setCnpj(cnpj);
            novoFornecedor.setTelefone(telefone);
            novoFornecedor.setLogradouro(logradouro);
            novoFornecedor.setNumero(numero);
            novoFornecedor.setEstado(estado);
            novoFornecedor.setBairro(bairro);
            novoFornecedor.setComplemento(complemento);
            novoFornecedor.setCidade(cidade);
            novoFornecedor.setCep(cep);

            supplierService.cadastrarFornecedor(novoFornecedor);

            limparCamposFornecedor();

            atualizarTabelaFornecedores();

        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao adicionar fornecedor: " + e.getMessage());
        }
    }

    private void limparCamposFornecedor() {
        nomeSupplierField.clear();
        emailSupplierField.clear();
        cnpjSupplierField.clear();
        telefoneSupplierField.clear();
        logradouroSupplierField.clear();
        numeroSupplierField.clear();
        estadoSupplierField.clear();
        bairroSupplierField.clear();
        complementoSupplierField.clear();
        cidadeSupplierField.clear();
        cepSupplierField.clear();
    }

    private void atualizarTabelaFornecedores() {
        List<SupplierModel> fornecedores = supplierService.mostrarTodosOsFornecedores();
        ObservableList<SupplierModel> fornecedoresData = FXCollections.observableArrayList(fornecedores);
        tableFornecedoresView.setItems(fornecedoresData);
    }

    @FXML
    private void adicionarProduto() {
        try {
            String nome = nomeProdField.getText();
            String descricao = descricaoProdField.getText();
            String preco = precoProdField.getText();
            String qtdEstoque = qtdProdField.getText();
            String img1 = img1ProdField.getText();
            String img2 = img2ProdField.getText();

            ProductModel novoProduto = new ProductModel();
            novoProduto.setNome(nome);
            novoProduto.setDescricao(descricao);
            novoProduto.setPreco(preco);
            novoProduto.setQtdEstoque(qtdEstoque);

            String[] imagens = { img1, img2 };
            novoProduto.setImagens(imagens);

            Long fornecedorId = obterFornecedorId();

            productService.cadastrarProduto(novoProduto, fornecedorId);

            limparCamposProduto();

            atualizarTabelaProdutos();

        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    private Long obterFornecedorId() {
        SupplierModel fornecedorSelecionado = (SupplierModel) fornecedorProdutoComboBox.getSelectionModel()
                .getSelectedItem();
        if (fornecedorSelecionado != null) {
            return fornecedorSelecionado.getId();
        } else {
            System.err.println("Nenhum fornecedor selecionado.");
            return null;
        }
    }

    private void limparCamposProduto() {
        nomeProdField.clear();
        descricaoProdField.clear();
        precoProdField.clear();
        qtdProdField.clear();
        img1ProdField.clear();
        img2ProdField.clear();
    }

    private void atualizarTabelaProdutos() {
        List<ProductModel> produtos = productService.mostrarTodosOsProdutos();
        ObservableList<ProductModel> produtosData = FXCollections.observableArrayList(produtos);
        tableProdutosView.setItems(produtosData);
    }

    private void atualizarProdutoComboBox() {
        try {
            fornecedorProdutoComboBox.setCellFactory(param -> new ListCell<>() {
                @Override
                protected void updateItem(SupplierModel item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getNome());
                    }
                }
            });
        } catch (Exception e) {
            System.out.println("Erro ao atualizar o ComboBox de fornecedores");
        }

        List<SupplierModel> fornecedores = supplierService.mostrarTodosOsFornecedores();
        fornecedorProdutoComboBox.setItems(FXCollections.observableArrayList(fornecedores));
    }

    @FXML
    private void gerarRelatorio() {
        try {
            String nomeRelatorio = (String) relatorioComboMenu.getSelectionModel().getSelectedItem();
            if (nomeRelatorio == null) {
                throw new IllegalArgumentException("Nenhum relatório selecionado.");
            }

            String endpointUrl = "http://localhost:8080/anime/api/relatorios/gerar?nomeRelatorio=" + nomeRelatorio
                    + "&tipoRelatorio=pdf";

            exibirRelatorioEmPopup(endpointUrl);

        } catch (IllegalArgumentException e) {
            System.err.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    @SuppressWarnings("deprecation")
    private void exibirRelatorioEmPopup(String url) {
        Stage primaryStage = new Stage();

        PDFDisplayer displayer = new PDFDisplayer();

        Scene scene = new Scene(displayer.toNode());
        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);

        primaryStage.show();

        try {displayer.loadPDF(new URL(url));} 
        catch (MalformedURLException e) {} 
        catch (IOException e) {}
    }

    private <T> void adicionarMenuContextoDeletar(TableView<T> tabela, Consumer<Long> deletarEntidade) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem deletarItem = new MenuItem("Deletar");

        contextMenu.getItems().add(deletarItem);

        deletarItem.setOnAction(event -> {

            T entidadeSelecionada = tabela.getSelectionModel().getSelectedItem();

            if (entidadeSelecionada != null) {
                try {
                    Method getIdMethod = entidadeSelecionada.getClass().getMethod("getId");
                    Long id = (Long) getIdMethod.invoke(entidadeSelecionada);
                    deletarEntidade.accept(id);

                    tabela.getItems().remove(entidadeSelecionada);

                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    System.err.println("Erro ao obter o ID da entidade: " + e.getMessage());
                }
            } else {
                System.err.println("Nenhuma linha selecionada.");
            }
        });

        tabela.setContextMenu(contextMenu);

        tabela.setOnMousePressed(event -> {
            if (event.isSecondaryButtonDown()) {
                contextMenu.show(tabela, event.getScreenX(), event.getScreenY());
            }
        });
    }

    public static class LongStringConverter extends StringConverter<Long> {

        @Override
        public String toString(Long object) {
            if (object == null) {
                return "";
            }
            return object.toString();
        }

        @Override
        public Long fromString(String string) {
            if (string == null || string.isEmpty()) {
                return null;
            }
            return Long.valueOf(string);
        }
    }
}
