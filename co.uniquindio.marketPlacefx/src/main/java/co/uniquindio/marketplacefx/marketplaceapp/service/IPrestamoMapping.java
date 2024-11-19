package co.uniquindio.marketplacefx.marketplaceapp.service;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.SugerenciaDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.UsuarioDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDto;
import co.uniquindio.marketplacefx.marketplaceapp.model.Producto;
import co.uniquindio.marketplacefx.marketplaceapp.model.Usuario;
import co.uniquindio.marketplacefx.marketplaceapp.model.Vendedor;

import java.util.Collection;
import java.util.List;

public interface IPrestamoMapping {
    List<VendedorDto>getVendedores(List<Vendedor> listaVendedor);
    VendedorDto vendedorToVendedorDto(Vendedor vendedor);
    Vendedor vendedorDtoToVendedor(VendedorDto vendedorDto);
    List<UsuarioDto> usuarioToUsuarioDto(List<Usuario> listaUsuarios);
    List<ProductoDto> listaProductosTolistaProductosDto(List<Producto> productos);
    Producto ProductoDtoToProducto(ProductoDto productoDto);

    List<SugerenciaDto> VendedorToSugerenciaDto(List<Vendedor> vendedors);
}
