package mx.erick.library;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import mx.erick.library.controller.BookController;
import mx.erick.library.domain.Author;
import mx.erick.library.domain.Book;
import mx.erick.library.service.interfaces.BookServiceI;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerIndexTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	BookServiceI bookService;
	
	@InjectMocks
	BookController bookController;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@BeforeEach
	public void setup() {
		Mockito.reset(bookService);
		this.mockMvc = MockMvcBuilders.standaloneSetup(
				bookController).build();
		ReflectionTestUtils.setField(
				bookController, "bookService", bookService);
	}
	
	@Test
	public void happyPath() throws Exception {
		
		when( bookService.getAll() ).thenReturn( new ArrayList<Book>() );
		
		MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get("/book")
				).andExpect( status().isAccepted() )
				.andReturn();
		String content = result.getResponse().getContentAsString();
		assertEquals("[]", content);
	}
	@Test
	public void happyPathWithElements() throws Exception {
		
		when( bookService.getAll() ).thenReturn( getListOfBooks() );
		
		MvcResult result = mockMvc.perform( MockMvcRequestBuilders.get("/book")
				).andExpect( status().isAccepted() )
				.andReturn();
		String content = result.getResponse().getContentAsString();
		CollectionType javaType = mapper.getTypeFactory()
			      .constructCollectionType(List.class, Book.class);
		List<Book> listR = mapper.readValue(content, javaType);
		
		for (Book book : getListOfBooks()) {
			assertThat(listR).contains(book);
		}
		
	}
	
	private List<Book> getListOfBooks() {
		List<Book> myList = new ArrayList<Book>();
		Author author = Author.builder().id(1l).name("Author").build();
		for (Long i = (long) 1; i <= 2; i++) {
			myList.add( Book.builder().id(i).name("name: "+i).author(author).build() );
		}
		return myList;
	}
	
	
}
