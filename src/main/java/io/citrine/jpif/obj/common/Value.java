package io.citrine.jpif.obj.common;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Information about a scalar, vector, or matrix, or a list of one of those.
 *
 * @author Kyle Michel
 */
public class Value extends Pio {

    /**
     * Set the name of this value.
     *
     * @param name String with the name of this value.
     * @return This object.
     */
    @JsonSetter(value = "name")
    public Value setName(final String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the name of this value.
     *
     * @return String with the name of this value.
     */
    @JsonGetter(value = "name")
    public String getName() {
        return this.name;
    }

    /**
     * Set the list of scalar values.
     *
     * @param scalars List of {@link Scalar} objects.
     */
    @JsonSetter(value = "scalars")
    @JsonDeserialize(contentUsing = Scalar.Deserializer.class)
    protected void setScalars(final List<Scalar> scalars) { // Private since only Jackson should use it
        this.scalars = scalars;
    }

    /**
     * Add a scalar to this value.
     *
     * @param scalar {@link Scalar} object to add to this value.
     * @return This object.
     */
    public Value addScalar(final Scalar scalar) {
        if (this.scalars == null) {
            this.scalars = new ArrayList<>();
        }
        this.scalars.add(scalar);
        return this;
    }

    /**
     * Get the number of scalars stored in this value.
     *
     * @return Number of scalars stored in this value.
     */
    public int scalarsLength() {
        return (this.scalars == null) ? 0 : this.scalars.size();
    }

    /**
     * Get a scalar value at a set index.
     *
     * @param index Index of the scalar to get.
     * @return {@link Scalar} object at the set index.
     * @throws IndexOutOfBoundsException if the input index is out of range of the scalar list.
     */
    @JsonIgnore
    public Scalar getScalar(final int index) {
        if (this.scalars == null) {
            throw new IndexOutOfBoundsException("Attempting to access scalar " + index + " of " + this.scalarsLength());
        }
        return this.scalars.get(index);
    }

    /**
     * Get an {@link Iterable} object over the scalars stored in this value.
     *
     * @return {@link Iterable} object for iterating over the scalars in this value.
     */
    public Iterable<Scalar> scalars() {
        return (this.scalars == null) ? Collections.emptyList() : this.scalars;
    }

    /**
     * Get the full list of scalar values.
     *
     * @return List of {@link Scalar} objects stored in this value.
     */
    @JsonGetter(value = "scalars")
    protected List<Scalar> getScalars() { // Private since only Jackson should use it
        return this.scalars;
    }

    /**
     * Set the list of vectors stored by this value.
     *
     * @param vectors List of {@link Scalar} arrays that represent the vectors stored by this value.
     */
    @JsonSetter(value = "vectors")
    @JsonDeserialize(using = VectorsDeserializer.class)
    protected void setVectors(final List<Scalar[]> vectors) { // Private since only Jackson should use it
        this.vectors = vectors;
    }

    /**
     * Add a single vector to this value.
     *
     * @param vector {@link Scalar} array with the vector to add.
     * @return This object.
     */
    public Value addVector(final Scalar[] vector) {
        if (this.vectors == null) {
            this.vectors = new ArrayList<>();
        }
        this.vectors.add(vector);
        return this;
    }

    /**
     * Get the number of vectors stored by this value.
     *
     * @return Number of vectors stored by this value.
     */
    public int vectorsLength() {
        return (this.vectors == null) ? 0 : this.vectors.size();
    }

    /**
     * Get a vector stored by this value at a set index.
     *
     * @param index Index of the vector to get.
     * @return {@link Scalar} array with the vector at the set index.
     * @throws IndexOutOfBoundsException if the input index is out of range of the vector list.
     */
    @JsonIgnore
    public Scalar[] getVector(final int index) {
        if (this.vectors == null) {
            throw new IndexOutOfBoundsException("Attempting to access vector " + index + " of " + this.vectorsLength());
        }
        return this.vectors.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over the vectors stored by this value.
     *
     * @return {@link Iterable} object for iterating over the vectors stored by this value.
     */
    public Iterable<Scalar[]> vectors() {
        return (this.vectors == null) ? Collections.emptyList() : this.vectors;
    }

    /**
     * Get the list of vectors stored by this value.
     *
     * @return List of {@link Scalar} arrays with the vectors stored by this value.
     */
    @JsonGetter(value = "vectors")
    protected List<Scalar[]> getVectors() { // Private since only Jackson should use it
        return this.vectors;
    }

    /**
     * Set the list of matrices stored by this value.
     *
     * @param matrices List of {@link Scalar} arrays of arrays that represent the matrices stored by this value.
     */
    @JsonSetter(value = "matrices")
    @JsonDeserialize(using = MatricesDeserializer.class)
    protected void setMatrices(final List<Scalar[][]> matrices) { // Private since only Jackson should use it
        this.matrices = matrices;
    }

    /**
     * Add a single matrix to this value.
     *
     * @param matrix {@link Scalar} array of arrays with the matrix to add.
     * @return This object.
     */
    public Value addMatrix(final Scalar[][] matrix) {
        if (this.matrices == null) {
            this.matrices = new ArrayList<>();
        }
        this.matrices.add(matrix);
        return this;
    }

    /**
     * Get the number of matrices stored by this value.
     *
     * @return Number of matrices stored by this value.
     */
    public int matricesLength() {
        return (this.matrices == null) ? 0 : this.matrices.size();
    }

    /**
     * Get a matrix stored by this value at a set index.
     *
     * @param index Index of the matrix to get from this value.
     * @return {@link Scalar} array of arrays for the matrix at the set index.
     * @throws IndexOutOfBoundsException if the input index is out of range of the list of matrices.
     */
    @JsonIgnore
    public Scalar[][] getMatrix(final int index) {
        if (this.matrices == null) {
            throw new IndexOutOfBoundsException("Attempting to access matrix " + index + " of "
                    + this.matricesLength());
        }
        return this.matrices.get(index);
    }

    /**
     * Get an {@link Iterable} object to iterate over the list of matrices stored by this value.
     *
     * @return {@link Iterable} object for iterating over the matrices stored by this value.
     */
    public Iterable<Scalar[][]> matrices() {
        return (this.matrices == null) ? Collections.emptyList() : this.matrices;
    }

    /**
     * Get the list of matrices stored by this value.
     *
     * @return List of {@link Scalar} arrays of arrays, each being a single matrix stored by this value.
     */
    @JsonGetter(value = "matrices")
    protected List<Scalar[][]> getMatrices() { // Private since only Jackson should use it
        return this.matrices;
    }

    /**
     * Set the units of this value.
     *
     * @param units String with the units of this value.
     * @return This object.
     */
    @JsonSetter(value = "units")
    public Value setUnits(final String units) {
        this.units = units;
        return this;
    }

    /**
     * Get the units stored by this value.
     *
     * @return String with the units stored by this value.
     */
    @JsonGetter(value = "units")
    public String getUnits() {
        return this.units;
    }

    @Override
    @JsonAnySetter
    public Value addUnsupportedField(final String key, final Object value) {
        super.addUnsupportedField(key, value);
        return this;
    }

    @Override
    public Value removeUnsupportedField(final String key) {
        super.removeUnsupportedField(key);
        return this;
    }

    @Override
    public Value clearUnsupportedFields() {
        super.clearUnsupportedFields();
        return this;
    }

    /** String with the name of the value. */
    private String name;

    /** List of scalar values. */
    private List<Scalar> scalars;

    /** List of vector values. */
    private List<Scalar[]> vectors;

    /** List of matrix values. */
    private List<Scalar[][]> matrices;

    /** Units of the value. */
    private String units;

    /**
     * Class to deserialize into a list of arrays of {@link Scalar} objects.
     *
     * @author Kyle Michel
     */
    public static class VectorsDeserializer extends JsonDeserializer<List<Scalar[]>> {

        @Override
        public List<Scalar[]> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            final JsonNode jsonNode = jsonParser.readValueAsTree();
            if (jsonNode.isArray()) {
                return fromArrayNode(jsonNode, deserializationContext);
            }
            throw deserializationContext.mappingException("Cannot deserialize to list of vectors");
        }

        /**
         * Convert from a JSON array to a list of {@link Scalar} arrays.
         *
         * @param jsonNode {@link JsonNode} that represents the array to convert.
         * @param deserializationContext {@link DeserializationContext} object for the parser.
         * @return List of arrays of {@link Scalar} objects.
         * @throws IOException if thrown from within this function.
         */
        protected List<Scalar[]> fromArrayNode(
                final JsonNode jsonNode, final DeserializationContext deserializationContext) throws IOException {
            if (jsonNode.size() == 0) {
                return Collections.emptyList();
            }
            else if (jsonNode.get(0).isArray()) {
                return fromList(jsonNode, deserializationContext);
            }
            else {
                return Collections.singletonList(fromVector(jsonNode, deserializationContext));
            }
        }

        /**
         * Convert from a list of lists to a list of {@link Scalar} arrays.
         *
         * @param jsonNode {@link JsonNode} to convert.
         * @param deserializationContext {@link DeserializationContext} object for the parser.
         * @return List of arrays of {@link Scalar} objects.
         * @throws IOException if thrown from within this function.
         */
        protected List<Scalar[]> fromList(
                final JsonNode jsonNode, DeserializationContext deserializationContext) throws IOException {
            final List<Scalar[]> res = new ArrayList<>(jsonNode.size());
            for (JsonNode i : jsonNode) {
                res.add(fromVector(i, deserializationContext));
            }
            return res;
        }

        /**
         * Convert from a list of values to an array of {@link Scalar} objects.
         *
         * @param jsonNode {@link JsonNode} to convert.
         * @param deserializationContext {@link DeserializationContext} object for the parser.
         * @return Array of {@link Scalar} objects.
         * @throws IOException if thrown from within this function.
         */
        protected Scalar[] fromVector(final JsonNode jsonNode, final DeserializationContext deserializationContext)
                throws IOException {
            final Scalar[] res = new Scalar[jsonNode.size()];
            final Scalar.Deserializer deserializer = new Scalar.Deserializer();
            for (int i = 0; i < jsonNode.size(); ++i) {
                final JsonParser jsonParser = jsonNode.get(i).traverse();
                jsonParser.nextToken();
                res[i] = deserializer.deserialize(jsonParser, deserializationContext);
            }
            return res;
        }
    }

    /**
     * Class to deserialize into a list of arrays of arrays of {@link Scalar} objects.
     *
     * @author Kyle Michel
     */
    public static class MatricesDeserializer extends JsonDeserializer<List<Scalar[][]>> {

        @Override
        public List<Scalar[][]> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            final JsonNode jsonNode = jsonParser.readValueAsTree();
            if (jsonNode.isArray()) {
                return fromArrayNode(jsonNode, deserializationContext, jsonParser.getCodec());
            }
            throw deserializationContext.mappingException("Cannot deserialize to list of matrices");
        }

        /**
         * Convert from a JSON array to a list of arrays of arrays of {@link Scalar} objects.
         *
         * @param jsonNode {@link JsonNode} to convert.
         * @param deserializationContext {@link DeserializationContext} object for the parser.
         * @param objectCodec {@link ObjectCodec} for the parser being used.
         * @return List of arrays of arrays of {@link Scalar} objects.
         * @throws IOException if thrown from within this function.
         */
        protected List<Scalar[][]> fromArrayNode(
                final JsonNode jsonNode, final DeserializationContext deserializationContext,
                final ObjectCodec objectCodec) throws IOException {
            if (jsonNode.size() == 0) {
                return Collections.emptyList();
            }
            else if (!jsonNode.get(0).isArray()) {
                throw deserializationContext.mappingException("Cannot deserialize to list of matrices");
            }
            else if ((jsonNode.get(0).size() > 0) && (jsonNode.get(0).get(0).isArray())) {
                return fromList(jsonNode, deserializationContext, objectCodec);
            }
            else {
                return Collections.singletonList(fromMatrix(jsonNode, deserializationContext, objectCodec));
            }
        }

        /**
         * Convert from a {@link JsonNode} object into a list of arrays of arrays of {@link Scalar} objects.
         *
         * @param jsonNode {@link JsonNode} to convert.
         * @param deserializationContext {@link DeserializationContext} object for the parser.
         * @param objectCodec {@link ObjectCodec} for the parser being used.
         * @return List of arrays of arrays of {@link Scalar} objects.
         * @throws IOException if thrown from within this function.
         */
        protected List<Scalar[][]> fromList(
                final JsonNode jsonNode, final DeserializationContext deserializationContext,
                final ObjectCodec objectCodec) throws IOException {
            final List<Scalar[][]> res = new ArrayList<>(jsonNode.size());
            for (JsonNode i : jsonNode) {
                res.add(fromMatrix(i, deserializationContext, objectCodec));
            }
            return res;
        }

        /**
         * Convert from a {@link JsonNode} object into an array of arrays of {@link Scalar} objects.
         *
         * @param jsonNode {@link JsonNode} object to convert into a matrix.
         * @param deserializationContext {@link DeserializationContext} object for the parser.
         * @param objectCodec {@link ObjectCodec} for the parser being used.
         * @return Array of arrays of {@link Scalar} objects.
         * @throws IOException if thrown from within this function.
         */
        protected Scalar[][] fromMatrix(
                final JsonNode jsonNode, final DeserializationContext deserializationContext,
                final ObjectCodec objectCodec) throws IOException {
            final JsonParser jsonParser = jsonNode.traverse(objectCodec);
            jsonParser.nextToken();
            final VectorsDeserializer deserializer = new VectorsDeserializer();
            final List<Scalar[]> matrix = deserializer.deserialize(jsonParser, deserializationContext);
            final Scalar[][] res = new Scalar[matrix.size()][];
            return matrix.toArray(res);
        }
    }
}