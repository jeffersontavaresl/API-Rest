package br.com.rest.APIRest.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.rest.APIRest.dto.DetalhesDoTopicoDto;
import br.com.rest.APIRest.dto.TopicoDto;
import br.com.rest.APIRest.form.AtualizacaoTopicoForm;
import br.com.rest.APIRest.form.TopicoForm;
import br.com.rest.APIRest.model.Topico;
import br.com.rest.APIRest.repository.CursoRepository;
import br.com.rest.APIRest.repository.TopicoRepository;

@RestController // ANOTAÇÃO PARA INFORMAR QUE ESSA CLASSE É UM RESTCONTROLLER
@RequestMapping("/topicos") // ANOTAÇÃO PARA INFORMAR O CAMINHO PARA ACESSA-LA
public class TopicosController {
	
	// INJEÇÃO DO REPOSITÓRIO PARA USAR OS MÉTODOS DO JPAREPOSITORY
	// ANOTAÇÃO INFORMA QUE O SPRING IRÁ GERENCIAR A INJEÇÃO
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoRepository cursoRepository;
	
	/**  Esse método vai listar todos os tópicos salvos no banco, usando a paginação do método pageable
	 *  @requestparam do string está falso pois não é um parametro obrigatório
	 *	a paginação default é organizada pelo id, em ordem crescente, na página 0 com 10 itens na página 
	 * @param nomeCurso
	 * @param paginacao
	 * @return
	 */
	
	@GetMapping // anotação para informar o protocolo http para acessar esse método
	@Cacheable(value = "listaDeTopicos") // anotação para informar que as informações serão salvas no cache
	public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso,
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao){
				
		// se o nome do curso estiver nulo irá listar todos os tópicos seguindo a paginação
		if(nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(paginacao);
			return TopicoDto.converter(topicos);
		} else {
			// se o nome do curso for informado ele irá buscar apenas os tópicos com o nome do curso informado
			Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
			return TopicoDto.converter(topicos);
		}
	}
	
	/** método para cadastrar informações no banco 
	 * 
	 * @param form
	 * @param uriBuilder
	 * @return
	 */
	
	@PostMapping // anotação para informar o protocolo http para acessar esse método
	@CacheEvict(value = "listaDeTopicos", allEntries = true) // anotação para informar que quando esse metodo for chamado o cache seja limpo
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		
		Topico topico = form.converter(cursoRepository); // converte as informações passadas pelo usuário na entidade topico
		topicoRepository.save(topico); // salva no banco de dados. O metodo save já garante que caso ocorra um erro, ele dará um "rollback"
		
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();  // uri criada para chamar o método abaixo
		return ResponseEntity.created(uri).body(new TopicoDto(topico)); // chama o método 201 (criado) e mostra as informações que o usuário criou
		
	}
	
	/** metodo irá buscar uma informação específica do banco
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}") // anotação para informar o protocolo http para acessar esse método o com parametro id
	public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable Long id) {
	
		Optional<Topico> topico = topicoRepository.findById(id); // procura no banco o id informado pelo usuário na url
		if(topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoTopicoDto(topico.get()));	
		}
		return ResponseEntity.notFound().build(); // caso não ache a informação passada passa o código 404
	}
	
	/**
 	   método para atualizar uma informação no banco com as informações passadas pelo usuário 
	 * @param id
	 * @param form
	 * @return
	 */
	
	@PutMapping("/{id}") // anotação para informar o protocolo http para acessar esse método o com parametro id
	@Transactional // anotação para garantir que as transações serão executados  e o rollback será feito caso ocorra algum erro.
	@CacheEvict(value = "listaDeTopicos", allEntries = true) // anotação para informar que quando esse metodo for chamado o cache seja limpo
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){			
		
		Optional<Topico> optional = topicoRepository.findById(id); // irá buscar no banco para verificar se já existe aquele id
		if(optional.isPresent()) {
			Topico topico = form.atualizar(id, topicoRepository); // caso esteja presente, irá atualziar as informações e passar o código 200
			return ResponseEntity.ok(new TopicoDto(topico));
		}
		return ResponseEntity.notFound().build();	 // caso não ache a informação passada passa o código 404
	}
	
	/** método para excluir um registro no banco de dados pelo id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}") // anotação para informar o protocolo http para acessar esse método o com parametro id
	@CacheEvict(value = "listaDeTopicos", allEntries = true) // anotação para informar que quando esse metodo for chamado o cache seja limpo
	@Transactional // anotação para garantir que as transações serão executados  e o rollback será feito caso ocorra algum erro.
	public ResponseEntity<?> remover(@PathVariable Long id){		

		Optional<Topico> optional = topicoRepository.findById(id); // irá verificar se realmente existe esse id 
		if(optional.isPresent()) {
			topicoRepository.deleteById(id); // caso existe irá realizar a exclusão e responder o código 200
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build(); // caso não ache a informação passada passa o código 404
	}
}
